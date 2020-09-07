package com.sviluppatoredisuccesso.webapp.service;

import java.util.List;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;

public interface ArticoliService<E>
{
	public List<Articoli> selectByDescrizione(String descrizione);
	
	public Articoli selectByCodArt(String codArt);
	
	public List<E> selectByFilter(String oggetto, String filter);
	
	
//	public Iterable<T> SelTutti();
	
//	public List<T> SelByDescrizione(String descrizione, Pageable pageable);
	
//	public void DelArticolo(T articolo);
//	
//	public void InsArticolo(T articolo);
}
