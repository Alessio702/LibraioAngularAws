package com.xantrix.webappspec.entities;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;

public class ArticoliSpec extends Articoli {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8717908017460090411L;
	
	// Libro	
	private String nome;
	
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
	
}
