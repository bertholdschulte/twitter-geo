package org.simple;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.simple.geo.CityLocation;
import org.simple.geo.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
 		SearchResults search = twitter.searchOperations().search(searchParameters);
		GeoData geoData = new GeoData();
 
		List<CityLocation> locations = search.getTweets().stream().parallel().filter(new Predicate<Tweet>() {
 
 			@Override
 			public boolean test(Tweet t) {
				geoData.increaseCount();
				if (t.getUser() != null && !StringUtils.isEmpty(t.getUser().getLocation())) {
					geoData.increaseProvided();
					return true;
				} else {
					return false;
 				}
 			}
	}).map(s -> mapper.find(s.getUser().getLocation())).filter(g -> g != null).collect(Collectors.toList());
		geoData.setLocations(locations);
		geoData.setHits(locations.size());
		return geoData;
 	}
	
	/*
	 * for test purposes
	 */
	public String readRaw(String query){
		ResponseEntity<String> forEntity = twitter.restOperations().getForEntity("https://api.twitter.com/1.1/search/tweets.json?q="+query+"&result_type=recent&count=100", String.class);
		return forEntity.getBody();
	}
}
