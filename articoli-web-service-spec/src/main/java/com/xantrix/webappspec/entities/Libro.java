package com.xantrix.webappspec.entities;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;

@Entity
@DiscriminatorValue("libro")
public class Libro extends Articoli {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8717908017460090411L;
	

	
	@Column(name = "NOME")
	private String nome;

	@Column(name = "REDAZIONE")
	private String redazione;



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
