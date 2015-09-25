package org.simple.geo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.simple.SimpleApplication;
import org.simple.geo.CountryMapper;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SimpleApplication.class)
public class CountryMapperTest {

	@Test
	public void test() {
		
		CountryMapper map = new CountryMapper();
		map.load();
		Assert.assertNotNull(map);
		Assert.assertEquals(244,map.getMap().size());
	}
	
}
