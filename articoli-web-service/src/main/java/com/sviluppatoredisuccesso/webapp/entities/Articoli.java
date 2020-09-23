package com.sviluppatoredisuccesso.webapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sviluppatoredisuccesso.webapp.dto.ArticoliDTO;

/**
 * This is a simple class that contains all informations about
 * a generic article.
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "categoria", discriminatorType = DiscriminatorType.STRING)
@Table(name = "ARTICOLIGENerico")
public class Articoli implements Serializable {
	private static final long serialVersionUID = 291353626011036772L;

	/**
	 * Field that represents the unique ID
	 * for an article object 
	 */
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	@SequenceGenerator(name = "my_seq", sequenceName = "hibernate_sequence", allocationSize = 1)
	private Integer id;

	/**
	 * Field that describes an article object.
	 */
	@Column(name = "DESCRIZIONE")
	private String descrizione;


	/**
	 * Field that represent the price for
	 * an article object
	 */
	@Transient
	private Double prezzo;


	/**
	 * Retrieve the Id of the article object.
	 * @return the article "Id"
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the Id of the article object.
	 * @param id The Id of the article. A variable of type integer.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Retrieve the description of the article object.
	 * @return the article field "descrizione" 
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Set the field "descrizione" of the article object.
	 * @param descrizione The description of the article object.
	 * 	      A variable of type String.
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * Retrieve the price of the article object.
	 * @return the article field "prezzo"
	 */
	public Double getPrezzo() {
		return prezzo;
	}

	
	/**
	 * Set the price of the article object.
	 * @param prezzo The price of the article object. 
	 * 		  A variable of type Double.
	 */
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	
	/**
	 * Retrieve the serialVersionUID of the class Articoli.
	 * @return the serialVersioneUID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	/**
	 * Method used to convert the entity class Articoli
	 * to ArticoliDTO.
	 * 
	 * @return dto of type ArticoliDTO
	 */
	public ArticoliDTO convertArticoliToDTO() {
		ArticoliDTO dto = new ArticoliDTO();
		dto.setId(this.id);
		dto.setDescrizione(this.descrizione);
		//dto.setPrezzo(this.prezzo);
		
		return dto;
	}
	
	
}
