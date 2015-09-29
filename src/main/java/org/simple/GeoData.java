package org.simple;

import java.util.List;

import org.simple.geo.CityLocation;

public class GeoData {

	private int hits;
	private int count;
	private List<CityLocation> locations;
	private int locationProvided;

	public void increaseProvided() {
		locationProvided++;
	}

	public void increaseCount() {
		count++;
	}

	public void setLocations(List<CityLocation> locations) {
		this.locations = locations;
	}

	public int getHits() {
		return hits;
	}

	public int getCount() {
		return count;
	}

	public List<CityLocation> getLocations() {
		return locations;
	}

	public int getLocationProvided() {
		return locationProvided;
	}

	public void setHits(int hits) {
		this.hits=hits;
	}

}
