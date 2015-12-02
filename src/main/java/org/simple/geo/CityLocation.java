package org.simple.geo;

import java.io.Serializable;

public class CityLocation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4030561407061179623L;
	private String code;
	private String latitude;
	private String longitude;
	private String name;
	private String timeZone;

	public CityLocation(String name, String latitude, String longitude, String code, String timeZone) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.name= name;
		this.code = code;
		this.timeZone = timeZone;

	}





	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String toString() {
		return String.format("code: %s, timezone: %s, name: %s, latitude: %s, longitude: %s", code, timeZone, getName(), latitude, longitude);
	}



	public String getName() {
		return name;
	}

}
