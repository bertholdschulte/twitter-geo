package org.simple.geo;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Soundex;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.simple.SimpleApplication;
import org.simple.geo.CountryMapper;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = SimpleApplication.class)
public class CountryMapperTest {

	@Test
	public void test() {
		
		CountryMapper map = new CountryMapper();
		System.out.println(map.map("Düsseldorf").toString());
		//Assert.assertEquals(244,map.getMap().size());
	}
	
	@Test
	public void testSoundEx() {
		// TODO Auto-generated method stub
		Soundex s = new Soundex();
		try {
			System.out.println(s.difference("Kreisfreie Stadt Bielefeld", "Bielefeld"));
		} catch (EncoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}