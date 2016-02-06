package org.simple;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.simple.geo.CityLocation;
import org.simple.geo.CityMapper;
import org.simple.geo.GeoData;
import org.simple.geo.GeoTweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.social.twitter.api.GeoCode;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchParameters.ResultType;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TwitterGeoService {

	@Autowired
	private Twitter twitter;

	@Autowired
	private CityMapper mapper;

	public GeoData readLocations(String query) {
		SearchParameters searchParameters = new SearchParameters(query);
		searchParameters.count(100);
		searchParameters.resultType(ResultType.RECENT);
		searchParameters.geoCode(new GeoCode(50.938056,6.956944,40000));
		SearchResults search = twitter.searchOperations().search(searchParameters);
		GeoData geoData = new GeoData();

		List<CityLocation> locations = search.getTweets().stream().filter(new Predicate<Tweet>() {

			@Override
			public boolean test(Tweet t) {
				geoData.increaseCount();
				if (t.getUser() != null && !StringUtils.isEmpty(t.getUser().getLocation()) && !StringUtils.isEmpty(t.getUser().getTimeZone())) {
					geoData.increaseProvided();
					return true;
				} else {
					return false;
				}
				
			}
		}).map(s -> mapper.find(s.getUser().getLocation(), s.getUser().getTimeZone())).filter(g -> g != null).collect(Collectors.toList());
		geoData.setLocations(locations);
		return geoData;
	}

	/*
	 * for test purposes
	 */
	public GeoTweet readRaw(String query) {
		ResponseEntity<GeoTweet> forEntity = twitter.restOperations().getForEntity(
				"https://api.twitter.com/1.1/search/tweets.json?q=" + query + "&result_type=recent&count=100&geocode=50,9,2000mi", GeoTweet.class);
		return forEntity.getBody();
	}

	public GeoData readExactLocations(String query) {
		// TODO Auto-generated method stub
		GeoData data = new GeoData();
		
		List<CityLocation> locations = Arrays.asList(readRaw(query).getStatuses()).stream().filter(s-> s != null && s.getGeo() != null && s.getGeo().getCoordinates().length==2).map(s->{
			data.increaseCount();
				data.increaseProvided();
				CityLocation cityLocation = new CityLocation(s.getText(),Double.parseDouble(s.getGeo().getCoordinates()[0]), Double.parseDouble(s.getGeo().getCoordinates()[1]), "XX", s.getCreated_at());
				//System.out.println(cityLocation);
				return cityLocation;
		}
			).collect(Collectors.toList());
		
		

		data.setLocations(locations);
		return data;
	}
}
