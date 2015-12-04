package org.simple.geo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Statuses {

	private Geo geo;
	private String text;

	public Geo getGeo() {
		// TODO Auto-generated method stub
		return geo;
	}

	public String getText(){
		return text;
	}
}
