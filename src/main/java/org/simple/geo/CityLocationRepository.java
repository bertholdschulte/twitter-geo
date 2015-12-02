package org.simple.geo;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CityLocationRepository {

	@Autowired
	private StringRedisTemplate template;

	public String find(String key) {
		return (String) template.opsForHash().get("city",key);
	}

	public Set<?> lookup(String pattern){
		return template.opsForHash().keys(pattern);
	}
	public void save(CityLocation cityLocation) {
		template.opsForHash().put("city", cityLocation.getName(), cityLocation.getName());
	}

}
