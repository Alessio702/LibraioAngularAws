package com.sviluppatoredisuccesso.webapp.service;

import java.util.List;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;

public interface ArticoliService<E>
{
	public List<Articoli> selectByDescrizione(String descrizione);
	
	public List<E> selectByFilter(String filter);
	
	public E selectById(Integer id);
	
	public E saveObject(E object);
	
	public void deleteObject(E object);
	
	public void deleteObjectById(Integer id);
	
//	public Iterable<T> SelTutti();
	
//	public List<T> SelByDescrizione(String descrizione, Pageable pageable);
	
//	public void DelArticolo(T articolo);
//	
//	public void InsArticolo(T articolo);
}
