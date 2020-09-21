package com.sviluppatoredisuccesso.webapp.dto;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;

public class ArticoliDto {

	private Integer id;
	private String descrizione;
	private Double prezzo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	
	
	public Articoli convertDtoToArticoli() {
		Articoli articoli = new Articoli();
		articoli.setDescrizione(this.descrizione);
		
		return articoli;
	}

}
