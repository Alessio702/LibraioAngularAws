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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sviluppatoredisuccesso.webapp.dto.ArticoliDto;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "categoria", discriminatorType = DiscriminatorType.STRING)
@Table(name = "ARTICOLI2")
public class Articoli implements Serializable {
	private static final long serialVersionUID = 291353626011036772L;

	@Id
	@Column(name = "CODART")
	@Size(min = 5, max = 20, message = "{Size.Articoli.codArt.Validation}")
	@NotNull(message = "{NotNull.Articoli.codArt.Validation}")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codArt;

	@Column(name = "DESCRIZIONE")
	@Size(min = 6, max = 80, message = "{Size.Articoli.descrizione.Validation}")
	private String descrizione;


	@Transient
	private Double prezzo;


	public Integer getCodArt() {
		return codArt;
	}

	public void setCodArt(Integer codArt) {
		this.codArt = codArt;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
//	method for convert entity to dto
	public ArticoliDto convertArticoliToDTO() {
		ArticoliDto dto = new ArticoliDto();
		dto.setCodArt(this.codArt);
		dto.setDescrizione(this.descrizione);
//		dto.setPrezzo(this.prezzo);
		
		return dto;
	}
	
	
}
