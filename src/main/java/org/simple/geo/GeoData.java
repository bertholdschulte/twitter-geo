package org.simple.geo;

import java.util.List;

public class GeoData {

	private int count = 0;
	private List<CityLocation> locations;
	private int locationProvided =0;

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
		if (locations != null) {
			return locations.size();
		} else {
			return 0;
		}
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

}
