package com.sviluppatoredisuccesso.webapp.service;

import java.util.List;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;

public interface ArticoliService 
{
	
	
	public List<Articoli> SelByDescrizione(String descrizione);
	
	public Articoli SelByCodArt(String codArt);
	
	
//	public Iterable<Articoli> SelTutti();
	
//	public List<Articoli> SelByDescrizione(String descrizione, Pageable pageable);
	
//	public void DelArticolo(Articoli articolo);
//	
//	public void InsArticolo(Articoli articolo);
}
