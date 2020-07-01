package com.apimobilidade.resources;

public class LinhaOnibus {

	private Long id;
	private Long idLinha;
	private String codigo;
	private String nome;
	
	/**
	 * 
	 */
	public LinhaOnibus() {
		super();
	}

	/**
	 * @param idLinha
	 * @param codigo
	 * @param nome
	 */
	public LinhaOnibus(Long idLinha, String codigo, String nome) {
		super();
		this.idLinha = idLinha;
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
	 * @return the idLinha
	 */
	public Long getIdLinha() {
		return idLinha;
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
	 * @param idLinha the idLinha to set
	 */
	public void setIdLinha(Long idLinha) {
		this.idLinha = idLinha;
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
		return "LinhaOnibus {" +
			"id=" + this.id +
	        ", idLinha=" + this.idLinha + 
	        ", codigo='" + this.codigo + "'" +
	        ", nome='" + this.nome + "'" +
        '}';
	}
}
