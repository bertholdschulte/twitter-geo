package org.simple.geo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

	private HashMap<String, CountryGeo> map = new HashMap<String, CountryGeo>();

	@PostConstruct
	protected void load() {

		InputStream is = null;
		is = this.getClass().getClassLoader().getResourceAsStream("countries-geo-location.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		 
		br.lines()
				.forEach(new Consumer<String>() {

					@Override
					public void accept(String s) {
						String[] geo = s.split("\t");;
						getMap().put(geo[0], new CountryGeo(geo[0], geo[1], geo[2], geo[3]));
					}
				});
		
	}

	public HashMap<String, CountryGeo> getMap() {
		return map;
	}

	
}
