package org.simple;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class TwitterConfiguration {

	@Bean
	public Twitter twitter(@Value("${twitter4j.oauth.consumerKey}") String consumerKey,
			@Value("${twitter4j.oauth.consumerSecret}") String consumerSecret,
			@Value("${twitter4j.oauth.accessToken}") String accessToken,
			@Value("${twitter4j.oauth.accessTokenSecret}") String accessTokenSecret) {
		return new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}

	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.US);
	    return slr;
	}
}