package org.simple.geo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Soundex;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

	InputStream is = this.getClass().getClassLoader().getResourceAsStream("cities1000.txt");;
	private Soundex soundex= new Soundex();
	BufferedReader br = new BufferedReader(new InputStreamReader(is));

	public CountryGeo map(String term) {
		CountryGeo cg =  new CountryGeo("", "0", "0", "xx");;
		String s = null;
		System.out.println(term);
		Stream<String> filter = br.lines().filter(new Predicate<String>() {

			@Override
			public boolean test(String t) {
				try {
					return t.contains(term.trim())|| term.trim().contains(t);//|| soundex.difference(t, term)>2;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		});
		
		Optional<String> findFirst = filter.findAny();
		if(findFirst != null && findFirst.isPresent()){
			s = findFirst.get();
			String[] geo = s.split("\t");
			cg= new CountryGeo(geo[8], geo[4], geo[5], geo[1].split(" ")[0]);
		}
		return cg;
	}
	
}
