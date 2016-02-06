package org.simple;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.simple.geo.CityLocation;
import org.simple.geo.GeoData;
import org.simple.geo.GeoTweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;

@Component
public class TwitterGeoService {

	@Autowired
	private Twitter twitter;

	public GeoTweet readRaw(String query) {
		String encodedQuery =  "";
		try {
			encodedQuery = URLEncoder.encode(query, "utf-8");
		} catch (UnsupportedEncodingException e) {
			//is supported
		}
		ResponseEntity<GeoTweet> forEntity = twitter.restOperations().getForEntity(
				"https://api.twitter.com/1.1/search/tweets.json?q=" + encodedQuery + "&result_type=recent&count=100&geocode=50,9,1000mi", GeoTweet.class);
		return forEntity.getBody();
	}

	public GeoData readExactLocations(String query) {
		GeoData data = new GeoData();
		
		List<CityLocation> locations = Arrays.asList(readRaw(query).getStatuses()).stream().filter(s-> s != null && s.getGeo() != null && s.getGeo().getCoordinates().length==2).map(s->{
			data.increaseCount();
				data.increaseProvided();
				CityLocation cityLocation = new CityLocation(s.getText(),Double.parseDouble(s.getGeo().getCoordinates()[0]), Double.parseDouble(s.getGeo().getCoordinates()[1]), "XX", s.getCreated_at());
				System.out.println(cityLocation);
				return cityLocation;
		}
			).collect(Collectors.toList());
		
		data.setLocations(locations);
		return data;
	}
}
