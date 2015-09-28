package org.simple.geo;

public class CityLocation {

	private String code;
	private String latitude;
	private String longitude;
	private String name;

	public CityLocation(String name, String latitude, String longitude, String code) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.code = code;

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
	
	public String toString(){
		return code+","+name+",lat:"+latitude+",long:"+longitude;
	}
}
