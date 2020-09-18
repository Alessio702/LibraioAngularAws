package com.xantrix.webapp.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.validation.constraints.Min;

@Document(collection = "DettListini")
public class DettListini implements Serializable {
	private static final long serialVersionUID = 8777751177774522519L;

	@Id
	private Integer id;

	@Size(min = 5, max = 20, message = "{Size.DettListini.codArt.Validation}")
	@NotNull(message = "{NotNull.DettListini.codArt.Validation}")
	private String codArt;

	@Min(value = (long) 0.01, message = "{Min.DettListini.prezzo.Validation}")
	private Double prezzo;

	@JsonBackReference
	@Indexed(name = "listino_index", direction = IndexDirection.ASCENDING)
	private Listini listino;

	public DettListini() {
	}

	@PersistenceConstructor
	public DettListini(String CodArt, Double Prezzo, Listini Listino) {
		this.codArt = CodArt;
		this.prezzo = Prezzo;
		this.listino = Listino;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodArt() {
		return codArt;
	}

	public void setCodArt(String codArt) {
		this.codArt = codArt;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public Listini getListino() {
		return listino;
	}

	public void setListino(Listini listino) {
		this.listino = listino;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
