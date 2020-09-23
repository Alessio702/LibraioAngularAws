package com.sviluppatoredisuccesso.webapp.dto;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;

/**
 * 
 * This is a DTO for Articoli,
 * a container class that exposes properties but no methods, 
 * used to pass data from the presentation layer to the service layer, and back.
 *
 */
public class ArticoliDto {

	/**
	 * Field that represents the unique ID
	 * for an ArticleDto object 
	 */
	private Integer id;
	
	/**
	 * Field that describes an ArticleDto object.
	 */
	private String descrizione;
	
	/**
	 * Field that represent the price for
	 * an ArticleDto object
	 */
	private Double prezzo;

	/**
	 * Retrieve the Id of the article object.
	 * @return the ArticleDto "Id"
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the Id of the ArticleDto object.
	 * @param id The Id of the ArticleDto. A variable of type integer.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Retrieve the description of the ArticleDto object.
	 * @return the ArticleDto field "descrizione" 
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Set the field "descrizione" of the ArticleDto object.
	 * @param descrizione The description of the ArticleDto object.
	 * 	      A variable of type String.
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	

	/**
	 * Retrieve the price of the ArticleDto object.
	 * @return the article field "prezzo"
	 */
	public Double getPrezzo() {
		return prezzo;
	}

	/**
	 * Set the price of the ArticleDto object.
	 * @param prezzo The price of the ArticleDto object. 
	 * 		  A variable of type Double.
	 */
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	
	

	/**
	 * Method used to convert the class ArticoliDTO
	 * to entity class Articoli.
	 * 
	 * @return articoli of type Articoli
	 */
	public Articoli convertDtoToArticoli() {
		Articoli articoli = new Articoli();
		articoli.setDescrizione(this.descrizione);
		
		return articoli;
	}

}
