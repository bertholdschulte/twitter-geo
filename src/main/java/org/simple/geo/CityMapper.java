package org.simple.geo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
		is = this.getClass().getClassLoader().getResourceAsStream("CitiesAbove1000.tsv");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		br.lines().forEach(new Consumer<String>() {

			@Override
			public void accept(String s) {
				String[] geo = s.split("\t");
				if (geo.length == 4) {
					map.put(geo[0].toLowerCase(), new CityLocation(geo[0], geo[1], geo[2], geo[3]));
 				}
			}
		});

	}

	public Map<String, CityLocation> getMap() {
 		return map;
 	}

	public CityLocation find(String location) {

		List<String> matchingKeys = map
				.subMap(String.valueOf((char) location.toLowerCase().charAt(0)),
						String.valueOf((char) (location.toLowerCase().charAt(0) + 1)))
				.keySet().stream().parallel().filter(new Predicate<String>() {
			 
		
			@Override
			public boolean test(String t) {
				try {
					boolean matches = !StringUtils.isEmpty(location) && t.matches(Pattern.quote(location.trim().toLowerCase()) + ".*") && 
							soundex.difference(t.toLowerCase(), location.toLowerCase().trim())==4;
 					if(matches){
						System.out.println(location + ":" +t);
					}
					return matches;// ||
																					// soundex.difference(t,
																					// term)>2;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		}).collect(Collectors.toList());
		System.out.println(matchingKeys.size());
		return matchingKeys.size() > 0 ? map.get(matchingKeys.get(0)) : null;
	}
}
