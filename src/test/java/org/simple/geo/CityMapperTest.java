package org.simple.geo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class CityMapperTest {

	//@Test
	public void test() {
		
		CityMapper map = new CityMapper();
		map.load();
		Assert.assertEquals("Berlin",map.getMap().get("Berlin").getName());
	}
	
	@Test
	public void testName() throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("b");
		list.add("c");
		List<String> l = list.stream().parallel().collect(Collectors.toList());
		System.out.println(l.size());
	}
}