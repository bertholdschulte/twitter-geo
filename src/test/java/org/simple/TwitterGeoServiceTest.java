package org.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { TwitterGeoApplication.class, TwitterGeoService.class })
@WebAppConfiguration
public class TwitterGeoServiceTest {

	@Autowired
	private TwitterGeoService twitterGeoService;

	@Test
	public void test() {

		GeoData locations = twitterGeoService.readLocations("Rolling Stones");
		locations.getLocations().forEach(l -> System.out.println(l));
	}

	//@Test
	public void testReadRaw() throws Exception {
		System.out.println(twitterGeoService.readRaw(""));
	}
}
