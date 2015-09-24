package org.simple.twitter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

@Component
public class TwitterGeoService {
	private static final String HOME_PATH = "/home/berthold/";

	private Twitter twitter; 
	
	public TwitterGeoService() {
		// TODO Auto-generated constructor stub
	}
	
	public TwitterGeoService(@Value("#{ systemProperties['consumerKey'] }") String consumerKey, @Value("#{ systemProperties['consumerSecret'] }")String consumerSecret, @Value("#{ systemProperties['accessToken'] }")String accessToken, @Value("#{ systemProperties['accessTokenSecret'] }")String accessTokenSecret) {
		twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}
	public void readGeo() {
		SearchResults search = twitter.searchOperations().search("#news");
		search.getTweets().forEach(s-> System.out.println(s));
	}
	
	private static void loadOAuthAccess() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(HOME_PATH + "twitter.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.getProperties().putAll(properties);
	}
}
