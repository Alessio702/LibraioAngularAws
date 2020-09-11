package com.xantrix.webappspec.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;


@Entity
@Table(name = "AWS_ProvaSpec")
public class ArticoliSpec extends Articoli {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8717908017460090411L;

	
	@Id
	@Column(name = "IDSPEC")
	@GeneratedValue
	private String idSpec;
	
	
	@Column(name = "NOME")
	private String nome;

	@Column(name = "REDAZIONE")
	private String redazione;


	
	
	public String getIdSpec() {
		return idSpec;
	}

	public void setIdSpec(String idSpec) {
		this.idSpec = idSpec;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRedazione() {
		return redazione;
	}

	public void setRedazione(String redazione) {
		this.redazione = redazione;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
