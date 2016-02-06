package org.simple.geo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CityLocationRepository {

	@Autowired
	private StringRedisTemplate template;

	public String find(String key) {
		return (String) template.opsForHash().get("city", key);
	}

	public Set<?> lookup(String pattern) {
		return template.opsForHash().keys(pattern);
	}

	public void save(CityLocation cityLocation) {
		template.opsForHash().put("city", cityLocation.getName(), cityLocation.getName());
	}

	public List<String> scan(String string) {
		Cursor<Entry<Object, Object>> scan = template.opsForHash().scan("city",
				new ScanOptions.ScanOptionsBuilder().match(string).count(1000l).build());
		List<String> results = new ArrayList<String>();
		while (scan.hasNext()) {
			results.add((String) scan.next().getKey());
			//TODO: issue hasNext vs next!
		}

		return results;
	}

}
