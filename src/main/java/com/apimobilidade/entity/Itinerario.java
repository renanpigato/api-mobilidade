package com.apimobilidade.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Itinerario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "linha_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Fetch(FetchMode.JOIN)
	private Linha linha;
	
	private Double latitude;
	private Double longitude;
	
	/**
	 * 
	 */
	public Itinerario() {
		super();
	}

	/**
	 * @param linha
	 * @param latitude
	 * @param longitude
	 */
	public Itinerario(Linha linha, Double latitude, Double longitude) {
		super();
		this.linha = linha;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the linha
	 */
	public Linha getLinha() {
		return linha;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param linha the linha to set
	 */
	public void setLinha(Linha linha) {
		this.linha = linha;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
