package com.xantrix.webapp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Size;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;


@DynamoDBTable(tableName = "Listini")
//@DynamoDBDocument
public class Listini implements Serializable {
	private static final long serialVersionUID = 1891268953233014092L;

	
	private String id;
	
	@Size(min = 10, max = 30, message = "{Size.Listini.descrizione.Validation}")
	private String descrizione;

	private String obsoleto;
	
	@JsonManagedReference
	private Set<DettListini> dettListini = new HashSet<>();

	public Listini() {
	}

	@JsonCreator
	public Listini(@JsonProperty("Id") String Id, @JsonProperty("descrizione") String Descrizione,
			@JsonProperty("obsoleto") String Obsoleto) {
		this.id = Id;
		this.descrizione = Descrizione;
		this.obsoleto = Obsoleto;
	}
	
	@DynamoDBHashKey(attributeName = "ListiniId")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDBAttribute(attributeName = "Descrizione")
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@DynamoDBAttribute(attributeName = "Obsoleto")
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
