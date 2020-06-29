package com.apimobilidade.resources;

public class LinhaOnibus {

	private Long id;
	private String codigo;
	private String nome;
	
	/**
	 * 
	 */
	public LinhaOnibus() {
		super();
	}

	/**
	 * @param id
	 * @param codigo
	 * @param nome
	 */
	public LinhaOnibus(Long id, String codigo, String nome) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
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
	public void setId(Long id) {
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
	        ", codigo='" + this.codigo + "'" +
	        ", nome='" + this.nome + "'" +
        '}';
	}
}
