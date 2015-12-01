package org.simple.geo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Statuses {

	private Geo geo;

	public Geo getGeo() {
		// TODO Auto-generated method stub
		return geo;
	}

}
