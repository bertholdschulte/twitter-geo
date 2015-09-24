package org.twitter;

import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

@Component
public class TwitterGeoService {

	private Twitter twitter;

	@Inject
	public TwitterGeoService(@Value("${twitter4j.oauth.consumerKey}") String consumerKey,
			@Value("${twitter4j.oauth.consumerSecret}") String consumerSecret,
			@Value("${twitter4j.oauth.accessToken}") String accessToken,
			@Value("${twitter4j.oauth.accessTokenSecret}") String accessTokenSecret) {
		twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}

	public Stream<String> readLocation(String query) {
		SearchResults search = twitter.searchOperations().search(query);
		return search.getTweets().stream().filter(new Predicate<Tweet>() {

			@Override
			public boolean test(Tweet t) {
				return t.getUser() != null & t.getUser().getLocation() != null ? true : false;
			}
		}).map(s -> s.getUser().getLocation());
	}

}
