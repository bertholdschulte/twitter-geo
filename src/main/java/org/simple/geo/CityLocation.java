package org.simple.geo;

import java.io.Serializable;

public class CityLocation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4030561407061179623L;
	private String code;
	private Double latitude;
	private Double longitude;
	private String name;
	private String timeZone;

	public CityLocation(String name, Double latitude, Double longitude, String code, String timeZone) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.code = code;
		this.timeZone = timeZone;

	}

	public CityLocation(String name, String latitude, String longitude, String code, String timeZone) {
		this(name, Double.parseDouble(latitude), Double.parseDouble(longitude), code, timeZone);
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public String toString() {
		return String.format("code: %s, timezone: %s, name: %s, latitude: %s, longitude: %s", code, getTimeZone(),
				getName(), latitude, longitude);
	}

	public String getName() {
		return name;
	}

	public String getTimeZone() {
		return timeZone;
	}

}
