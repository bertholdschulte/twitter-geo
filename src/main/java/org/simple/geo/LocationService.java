package org.simple.geo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationService {

	@Autowired
	private CityLocationRepository repo;
	
	public List<String> getLocation(String key) {
		
		ArrayList<String> locations = new ArrayList<String>();
		repo.find(key);
		return locations;
		// TODO Auto-generated method stub
		
	}

}