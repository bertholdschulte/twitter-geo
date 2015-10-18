package org.simple.geo;

public class CityLocation {

	private String code;
	private String latitude;
	private String longitude;
	private String name;
	private String timeZone;

	public CityLocation(String name, String latitude, String longitude, String code, String timeZone) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.code = code;
		this.timeZone = timeZone;

	}

	public String getCode() {
		return code;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getName() {
		return name;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public String toString() {
		return String.format("code: %s, timezone: %s, name: %s, latitude: %s, longitude: %s", code, timeZone, name, latitude, longitude);
	}
}
