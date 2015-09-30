package org.simple;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
public class TwitterConfiguration {

	@Bean
	public Twitter twitter(@Value("${twitter4j.oauth.consumerKey}") String consumerKey,
			@Value("${twitter4j.oauth.consumerSecret}") String consumerSecret,
			@Value("${twitter4j.oauth.accessToken}") String accessToken,
			@Value("${twitter4j.oauth.accessTokenSecret}") String accessTokenSecret) {
		return new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}

}