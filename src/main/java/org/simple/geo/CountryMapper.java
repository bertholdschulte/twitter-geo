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

	private HashMap<String, CityLocation> map = new HashMap<String, CityLocation>();

	@PostConstruct
	protected void load() {

		InputStream is = null;
		is = this.getClass().getClassLoader().getResourceAsStream("CitiesAbove1000.tsv");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		br.lines().forEach(new Consumer<String>() {

			@Override
			public void accept(String s) {
				String[] geo = s.split("\t");
				if (geo.length == 4) {
					getMap().put(geo[0], new CityLocation(geo[0], geo[1], geo[2], geo[3]));
				}
			}
		});

	}

	public HashMap<String, CityLocation> getMap() {
		return map;
	}

}
