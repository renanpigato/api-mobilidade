package com.apimobilidade.resources.linha;

public class Onibus {

	private final long id;
	private final int codigo;
	private final String nome;
	
	/**
	 * @param id
	 * @param codigo
	 * @param nome
	 */
	public Onibus(long id, int codigo, String nome) {
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
	public int getCodigo() {
		return codigo;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
}
