package org.simple.geo;

import static org.junit.Assert.*;

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
public class CityLocationRepositoryTest {

	@Autowired
	private CityLocationRepository cityLocationRepository;

	@Test
	public void testFindByName() {
		//cityLocationRepository.save(new CityLocation("Berlin", "0", "0", null, null));
		Assert.assertEquals("Berlin", cityLocationRepository.find("Berlin"));
	}

	@Test
	public void testLookup() throws Exception {
		 cityLocationRepository.lookup("city").stream().forEach(s->System.out.println(s));
	}
}
