package com.bcd.fraud;

import java.io.Serializable;

public class Geolocation implements Serializable {

	private static final long serialVersionUID = 5962937904767324730L;
	
	private Double latitude;
	private Double longitude;
	
	public Geolocation(){
	}
	
	public Geolocation(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public Double getLongitude() {
		return longitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	
	
}
