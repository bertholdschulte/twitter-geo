package org.simple.geo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoTweet {

	private Statuses[] statuses;

	public Statuses[] getStatuses(){
		return statuses;
	};

}
