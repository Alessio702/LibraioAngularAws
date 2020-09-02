package com.sviluppatoredisuccesso.webapp.service;

import java.util.List;

public interface ArticoliServiceGeneral 
{
	
	
	public List<Object> SelectByRedazione(String redazione);
	
	public Object SelectByFilter(String filter);
	
	
//	public Iterable<T> SelTutti();
	
//	public List<T> SelByDescrizione(String descrizione, Pageable pageable);
	
//	public void DelArticolo(T articolo);
//	
//	public void InsArticolo(T articolo);
}
