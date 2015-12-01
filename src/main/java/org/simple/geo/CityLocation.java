package org.simple.geo;

public class CityLocation {

	private String code;
	private double latitude;
	private double longitude;
	private String name;
	private String timeZone;

	public CityLocation(String name, double latitude, double longitude, String code, String timeZone) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.name= name;
		this.code = code;
		this.timeZone = timeZone;

	}



	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String toString() {
		return String.format("code: %s, timezone: %s, name: %s, latitude: %s, longitude: %s", code, timeZone, getName(), latitude, longitude);
	}



	public String getName() {
		return name;
	}

}
