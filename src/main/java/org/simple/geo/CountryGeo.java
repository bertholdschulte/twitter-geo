package org.simple.geo;

public class CountryGeo {

	private String code;
	private String latitude;
	private String longitude;
	private String name;

	public CountryGeo(String code, String latitude, String longitude, String name) {
		this.code = code;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;

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
}
