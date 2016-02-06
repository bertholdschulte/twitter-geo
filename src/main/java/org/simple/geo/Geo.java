package org.simple.geo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Geo {

	private String[] coordinates;

	public String[] getCoordinates() {
		return coordinates;
	}

}
