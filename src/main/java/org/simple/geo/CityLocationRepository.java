package org.simple.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CityLocationRepository {

	@Autowired
	private StringRedisTemplate template;

	public String find(String string) {
		return  (String) template.opsForHash().get("city",string);
	}

	public void save(CityLocation cityLocation) {
		template.opsForHash().put("city", cityLocation.getName(), cityLocation.getName());
	}

}
