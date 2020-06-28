package com.apimobilidade.poatransporte.resources.linha;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Onibus {
	
	private long id;
	private String codigo;
	private String nome;
	
	/**
	 * 
	 */
	public Onibus() {
		super();
	}

	/**
	 * @param id
	 * @param codigo
	 * @param nome
	 */
	public Onibus(long id, String codigo, String nome) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Onibus {" +
	        "id=" + this.id + 
	        ", codigo=" + this.codigo +
	        ", nome=" + this.nome +
        '}';
	}
}
