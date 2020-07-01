package com.apimobilidade.classes;

public class ItinerarioPoaTransporte {

	private String lat;
	private String lng;
	
	/**
	 * 
	 */
	public ItinerarioPoaTransporte() {
		super();
	}
	/**
	 * @param lat
	 * @param lng
	 */
	public ItinerarioPoaTransporte(String lat, String lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * @return the lng
	 */
	public String getLng() {
		return lng;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * @param lng the lng to set
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	
}
