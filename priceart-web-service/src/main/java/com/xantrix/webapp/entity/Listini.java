package com.xantrix.webapp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonManagedReference;




@Document(collection = "Listini")
public class Listini implements Serializable {
	private static final long serialVersionUID = 1891268953233014092L;


	@Id
	private String id;

	@Size(min = 10, max = 30, message = "{Size.Listini.descrizione.Validation}")
	private String descrizione;

	private String obsoleto;

	@JsonManagedReference
	private Set<DettListini> dettListini = new HashSet<>();

	public Listini() {
	}

	@PersistenceConstructor
	public Listini(String Id, String Descrizione, String Obsoleto) {
		this.id = Id;
		this.descrizione = Descrizione;
		this.obsoleto = Obsoleto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getObsoleto() {
		return obsoleto;
	}

	public void setObsoleto(String obsoleto) {
		this.obsoleto = obsoleto;
	}

	public Set<DettListini> getDettListini() {
		return dettListini;
	}

	public void setDettListini(Set<DettListini> dettListini) {
		this.dettListini = dettListini;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
