package com.apimobilidade.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Linha {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long idLinha;
	private String codigo;
	private String nome;
	private String tipo;
	
	/**
	 * 
	 */
	public Linha() {
		super();
	}

	/**
	 * @param idLinha
	 */
	public Linha(Long idLinha) {
		super();
		this.idLinha = idLinha;
	}

	/**
	 * @param idLinha
	 * @param codigo
	 * @param nome
	 * @param tipo
	 */
	public Linha(Long idLinha, String codigo, String nome, String tipo) {
		super();
		this.idLinha = idLinha;
		this.codigo = codigo;
		this.nome = nome;
		this.tipo = tipo;
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
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param idLinha the id to set
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

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "Linha {" +
			", id=" + this.id +
	        ", idLinha=" + this.idLinha + 
	        ", codigo='" + this.codigo + "'" +
	        ", nome='" + this.nome + "'" +
	        ", tipo='" + this.tipo+ "'" +
        '}';
	}
}
