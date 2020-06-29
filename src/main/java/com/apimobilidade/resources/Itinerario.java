package com.apimobilidade.resources;

import java.util.List;

import com.apimobilidade.classes.Localizacao;

public class Itinerario {

	private LinhaOnibus linha;
	private List<Localizacao> localizacoes;
	
	
	/**
	 * @param linha
	 * @param localizacoes
	 */
	public Itinerario(LinhaOnibus linha) {
		super();
		this.linha = linha;
	}
	
	/**
	 * @param linha
	 * @param localizacoes
	 */
	public Itinerario(LinhaOnibus linha, List<Localizacao> localizacoes) {
		super();
		this.linha = linha;
		this.localizacoes = localizacoes;
	}

	/**
	 * @return the linha
	 */
	public LinhaOnibus getLinha() {
		return linha;
	}

	/**
	 * @return the localizacoes
	 */
	public List<Localizacao> getLocalizacoes() {
		return localizacoes;
	}

	/**
	 * @param linha the linha to set
	 */
	public void setLinha(LinhaOnibus linha) {
		this.linha = linha;
	}

	/**
	 * @param localizacoes the localizacoes to set
	 */
	public void setLocalizacoes(List<Localizacao> localizacoes) {
		this.localizacoes = localizacoes;
	}
	
	public void addLocalizacao(Localizacao l) {
		this.localizacoes.add(l);
	}
}
