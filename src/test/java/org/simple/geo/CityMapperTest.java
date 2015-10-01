package org.simple.geo;

import org.junit.Assert;
import org.junit.Test;

public class CityMapperTest {

	//@Test
	public void test() {
		
		CityMapper map = new CityMapper();
		map.load();
		Assert.assertEquals("Berlin",map.getMap().get("Berlin").getName());
	}
	
}