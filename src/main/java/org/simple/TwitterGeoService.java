package org.simple;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.simple.geo.CityLocation;
import org.simple.geo.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlProcessor.ResolutionMethod;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchParameters.ResultType;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

@Component
public class TwitterGeoService {

	private Twitter twitter;
	@Autowired
	private CityMapper mapper;

	@Inject
	public TwitterGeoService(@Value("${twitter4j.oauth.consumerKey}") String consumerKey,
			@Value("${twitter4j.oauth.consumerSecret}") String consumerSecret,
			@Value("${twitter4j.oauth.accessToken}") String accessToken,
			@Value("${twitter4j.oauth.accessTokenSecret}") String accessTokenSecret) {
		twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);

	}

	public GeoData readLocations(String query) {
		SearchParameters searchParameters = new SearchParameters(query);
		searchParameters.count(100);
		searchParameters.resultType(ResultType.RECENT);
		SearchResults search = twitter.searchOperations().search(searchParameters);
		GeoData geoData = new GeoData();

		List<CityLocation> locations = search.getTweets().stream().filter(new Predicate<Tweet>() {

			@Override
			public boolean test(Tweet t) {
				geoData.increaseCount();
				if (t.getUser() != null && t.getUser().getLocation() != null) {
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

}
