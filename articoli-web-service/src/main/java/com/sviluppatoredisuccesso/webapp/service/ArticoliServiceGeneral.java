package com.sviluppatoredisuccesso.webapp.service;

import java.util.List;

public interface ArticoliServiceGeneral<E> 
{
	
	
	public List<Object> SelectByRedazione(String redazione);
	
	public List<E> SelectByFilter(E tipoOggetto, String filter);
	
	
//	public Iterable<T> SelTutti();
	
//	public List<T> SelByDescrizione(String descrizione, Pageable pageable);
	
//	public void DelArticolo(T articolo);
//	
//	public void InsArticolo(T articolo);
}
