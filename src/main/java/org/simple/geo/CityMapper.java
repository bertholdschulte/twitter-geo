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
				if (geo.length >3) { // depends on csv/txt files
				//	map.put(geo[0], new CityLocation(geo[0], geo[1], geo[2], geo[3])); // depends on csv/txt files
					map.put(geo[1], new CityLocation(geo[1], geo[4], geo[5], geo[8]));
 				}
			}
		});

	}

	public Map<String, CityLocation> getMap() {
 		return map;
 	}

	public CityLocation find(String location) {

		Optional<String> matchingKeys = map
				.keySet().stream().parallel().filter(new Predicate<String>() {
		
			@Override
			public boolean test(String t) {
				try {
					
					boolean matches = !StringUtils.isEmpty(location) && location.matches(".*"+Pattern.quote(t)+"( .*|$)")
							&& soundex.difference(t.toLowerCase(), location.toLowerCase().trim())==4;
 					if(matches){
 						//System.out.println("city:" +t);
 						//System.out.println(location +":" +t);
 						//System.out.println(location.replace(t, String.format("<START:location>%s<END>",t)));
					}
					return matches;
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				return false;
			}
		}).findFirst();//.collect(Collectors.toList());
		//System.out.println(matchingKeys.size());
		if(matchingKeys.isPresent()){
			return  map.get(matchingKeys.get());
		}else{
			return null;
		}
		//return matchingKeys.size() > 0 ? map.get(matchingKeys.get(0)) : null;
	}
}
