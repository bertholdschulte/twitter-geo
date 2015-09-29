package org.simple;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.simple.TwitterGeoApplication;
import org.simple.TwitterGeoService;
import org.simple.geo.CityLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.social.twitter.api.Place;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TwitterGeoApplication.class,TwitterGeoService.class})
@TestPropertySource(locations="classpath:twitter.properties")
@WebAppConfiguration
public class TwitterGeoServiceTest {

	@Autowired
	private TwitterGeoService twitterGeoService;

	@Test
	public void test() {
		
		List<CityLocation> locations = twitterGeoService.readLocations("Rolling Stones");
		locations.forEach(l->System.out.println(l));
	}

}

