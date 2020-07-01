package com.apimobilidade.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Localizacao {

	private double latitude;
	private double longitude;
	
	@JsonIgnore
	private double maximaLatitude;
	
	@JsonIgnore
	private double minimaLatitude;
	
	@JsonIgnore
	private double maximaLongitude;
	
	@JsonIgnore
	private double minimaLongitude;
	
	/**
	 * 
	 */
	public Localizacao() {
		super();
	}

	/**
	 * @param latitude
	 * @param longitude
	 */
	public Localizacao(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the maximaLatitude
	 */
	public double getMaximaLatitude() {
		return this.maximaLatitude;
	}

	/**
	 * @return the minimaLatitude
	 */
	public double getMinimaLatitude() {
		return this.minimaLatitude;
	}

	/**
	 * @return the maximaLongitude
	 */
	public double getMaximaLongitude() {
		return this.maximaLongitude;
	}

	/**
	 * @return the minimaLongitude
	 */
	public double getMinimaLongitude() {
		return this.minimaLongitude;
	}
	
	public void calcularRaioAtuacao(double raio) {
		
		Localizacao localizacaoMaxima = this.calcularPonto(raio);
		this.maximaLatitude = localizacaoMaxima.getLatitude();
		this.maximaLongitude = localizacaoMaxima.getLongitude();
		
		Localizacao localizacaoMinima = this.calcularPonto((raio * -1));
		this.minimaLatitude = localizacaoMinima.getLatitude();
		this.minimaLongitude = localizacaoMinima.getLongitude();
	}
	
	private Localizacao calcularPonto(double d) {
		
		double raio = 6371 * 1000;
		
		double latitude  = this.latitude;
		double longitude = this.longitude;
		
		double lat = Math.asin(
			Math.sin(this.toRad(latitude)) * Math.cos(d / raio)
			+ Math.cos(this.toRad(latitude)) * Math.sin(d / raio) * Math.cos(0)
		);
		
		double rolamento = this.toRad(90);
		
		double lng = this.toRad(longitude)
			+ Math.atan2(
				Math.sin(rolamento) * Math.sin(d / raio) * Math.cos(this.toRad(latitude)),
				Math.cos(d / raio) - Math.sin(this.toRad(latitude)) * Math.sin(lat)
			)
		;
		
		return new Localizacao(this.toDegree(lat), this.toDegree(lng));
	}
	
	private double toRad(double degree) {
		return degree * Math.PI / 180;
	}
	
	private double toDegree(double radianos) {
		return radianos * 180 / Math.PI;
	}
	
	@Override
	public String toString() {
		return "Localizacao {" + 
	        "latitude=" + this.latitude +
	        ", longitude=" + this.longitude +
	        ", maximaLatitude=" + this.maximaLatitude +
	        ", minimaLatitude=" + this.minimaLatitude +
	        ", maximaLongitude=" + this.maximaLongitude +
	        ", minimaLongitude=" + this.minimaLongitude +
        '}';
	}
}
