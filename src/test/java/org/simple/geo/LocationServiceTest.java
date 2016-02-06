package org.simple.geo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.simple.TwitterGeoApplication;
import org.simple.TwitterGeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { TwitterGeoApplication.class, TwitterGeoService.class })
@WebAppConfiguration
public class LocationServiceTest {

	@Autowired
	private LocationService service;
	
	@Test
	public void test() {
		List<String> location = service.getLocation("Col");
		Assert.assertEquals(3, location.size());
	}

}
