package org.simple.geo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Statuses {

	private Geo geo;
	private String text;
	private String created_at;

	public Geo getGeo() {
		return geo;
	}

	public String getText(){
		return text;
	}

	public String getCreated_at() {
		return created_at;
	}
}
