package org.simple.geo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.language.Soundex;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CityMapper {

	private SortedMap<String, CityLocation> map = new TreeMap<String, CityLocation>();
	private Soundex soundex = new Soundex();

	@PostConstruct
	protected void load() {

		InputStream is = null;
		is = this.getClass().getClassLoader().getResourceAsStream("cities5000.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		br.lines().forEach(new Consumer<String>() {

			@Override
			public void accept(String s) {
				String[] geo = s.split("\t");
				if (geo.length >17) { // depends on csv/txt files
				//	map.put(geo[0], new CityLocation(geo[0], geo[1], geo[2], geo[3])); // depends on csv/txt files
					map.put(geo[1], new CityLocation(geo[1], geo[4], geo[5], geo[8], geo[17]));
 				}
			}
		});

	}

	public Map<String, CityLocation> getMap() {
 		return map;
 	}

	public CityLocation find(String location, String timeZone) {

		Optional<CityLocation> matches = map
				.values().stream().filter(new Predicate<CityLocation>() {
		
			@Override
			public boolean test(CityLocation t) {
				try {
					
					boolean isMatch = !StringUtils.isEmpty(location) && location.matches(".*"+Pattern.quote(t.getName())+"( .*|$)")
							&& soundex.difference(t.getName().toLowerCase(), location.toLowerCase().trim())==4
							&& !StringUtils.isEmpty(t.getTimeZone()) && t.getTimeZone().toLowerCase().contains(timeZone.toLowerCase());
 					if(isMatch){
 						System.out.println(t);
					}
					return isMatch;
				} catch (Throwable e) {
					//e.printStackTrace();
				}
				return false;
			}
		}).findFirst();
		if(matches.isPresent()){
			return  matches.get();
		}else{
			return null;
		}
	}
}
