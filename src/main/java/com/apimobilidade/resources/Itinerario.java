package com.apimobilidade.resources;

import java.util.List;
import java.util.ListIterator;

import com.apimobilidade.entity.GeoPosition;
import com.apimobilidade.resources.linha.Onibus;

public class Itinerario {

	private Onibus linha;
	private List<GeoPosition> localizacoes;
	
	/**
	 * @param linha
	 * @param localizacoes
	 */
	public Itinerario(Onibus linha, List<GeoPosition> localizacoes) {
		super();
		this.linha = linha;
		this.localizacoes = localizacoes;
	}

	/**
	 * @return the linha
	 */
	public Onibus getLinha() {
		return linha;
	}

	/**
	 * @return the localizacoes
	 */
	public List<GeoPosition> getLocalizacoes() {
		return localizacoes;
	}

	/**
	 * @param linha the linha to set
	 */
	public void setLinha(Onibus linha) {
		this.linha = linha;
	}

	/**
	 * @param localizacoes the localizacoes to set
	 */
	public void setLocalizacoes(List<GeoPosition> localizacoes) {
		this.localizacoes = localizacoes;
	}
}
