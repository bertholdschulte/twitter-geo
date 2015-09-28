package org.simple.geo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.codec.language.Soundex;
import org.springframework.stereotype.Component;

@Component
public class CountryMapperStream {

	private Soundex soundex = new Soundex();

	public CityLocation map(String term) {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("Cities1000.tsv");
		;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		System.out.println(term);
		List<CityLocation> geos = br.lines().parallel().filter(new Predicate<String>() {

			@Override
			public boolean test(String t) {
				String[] location = t.split("\t");
				if (location.length > 0) {
					try {// mapping ausarbeiten, geodaten anpassen
						return location[0].matches(".*" + Pattern.quote(term.trim()) + ".*");// ||
						// soundex.difference(t,
						// term)>2;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return false;
			}
		}).map(s -> {
			String[] geo = s.split("\t");
			if (geo.length == 4) {
				CityLocation countryGeo = new CityLocation(geo[0], geo[1], geo[2], geo[3]);
				System.out.println(countryGeo);
				return countryGeo;
			} else {
				return new CityLocation("", "0", "0", "xx");
			}
		}).collect(Collectors.toList());
		System.out.println(geos.size());
		return geos.size() > 0 ? geos.get(0) : new CityLocation("", "0", "0", "xx");
	}

}
