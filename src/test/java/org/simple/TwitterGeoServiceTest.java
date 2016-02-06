package org.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.simple.geo.GeoData;
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
	public void testReadRaw() throws Exception {
		System.out.println(twitterGeoService.readRaw("marvin"));
	}
	
	@Test
	public void testGeo() throws Exception {
		String lat = twitterGeoService.readRaw("marvin").getStatuses()[0].getGeo().getCoordinates()[0];
		System.out.println(lat);
	}
	
	@Test
	public void testReadExactLocations() throws Exception {
		GeoData data = twitterGeoService.readExactLocations("marvin");
		System.out.println(data.getLocations().get(0).getLatitude());
		System.out.println(data.getLocations().get(0).getLongitude());
	}

}
