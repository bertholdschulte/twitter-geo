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
public class CountryMapper {

	private Soundex soundex= new Soundex();

	public CountryGeo map(String term) {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("cities1000.txt");;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		System.out.println(term);
		List<CountryGeo> geos = br.lines().parallel().filter(new Predicate<String>() {

			@Override
			public boolean test(String t) {
				try {//mapping ausarbeiten, geodaten anpassen
					return t.matches(".*/s"+Pattern.quote(term.trim())+"/t.*");//|| soundex.difference(t, term)>2;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		}).map(s-> {
				String[] geo = s.split("\t");
				CountryGeo countryGeo = new CountryGeo(geo[8], geo[4], geo[5], geo[1]);
				System.out.println(countryGeo);
				return countryGeo;
			}).collect(Collectors.toList());
		//System.out.println(geos.size());
		return geos.size()>0?geos.get(0):new CountryGeo("", "0", "0", "xx");
	}
	
}
