package com.apimobilidade.resources;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.apimobilidade.classes.Localizacao;

public class PontoTaxi {
	
	public static final String HEADER = new String(
		"NOME_DO_PONTO#LATITUDE#LONGITUDE#LONGITUDE#DATA_HORA_CADASTRO"
	);

	private Long id;
	private String nomePonto;
	private Localizacao localizacao;
	private Date dataHora;
	
	/**
	 * 
	 */
	public PontoTaxi() {
		super();
	}

	/**
	 * @param nomePonto
	 * @param localizacao
	 * @param dataHora
	 */
	public PontoTaxi(String nomePonto, Localizacao localizacao, Date dataHora) {
		super();
		this.nomePonto = nomePonto;
		this.localizacao = localizacao;
		this.dataHora = dataHora;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the nomePonto
	 */
	public String getNomePonto() {
		return nomePonto;
	}

	/**
	 * @return the localizacao
	 */
	public Localizacao getLocalizacao() {
		return localizacao;
	}

	/**
	 * @return the dataHora
	 */
	public Date getDataHora() {
		return dataHora;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param nomePonto the nomePonto to set
	 */
	public void setNomePonto(String nomePonto) {
		this.nomePonto = nomePonto;
	}

	/**
	 * @param localizacao the localizacao to set
	 */
	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	/**
	 * @param dataHora the dataHora to set
	 */
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	
	public String toString() {
		
		SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
		
		return 	this.nomePonto + "#" + 
				this.localizacao.getLatitude() + "#" +
				this.localizacao.getLongitude() + "#" +
				formatData.format(this.getDataHora())
		;
	}
}
