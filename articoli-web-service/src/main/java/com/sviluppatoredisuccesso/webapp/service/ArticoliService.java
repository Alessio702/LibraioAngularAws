package com.sviluppatoredisuccesso.webapp.service;

import java.util.List;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;

public interface ArticoliService<E extends Articoli>
{
	public abstract List<Articoli> selectByDescrizione(String descrizione);
	
	public abstract List<E> selectByFilter(String filter);
	
	public abstract E selectById(String codArt);
	
	public abstract E saveObject(E object);
	
	public abstract void deleteObject(E object);
	
	public abstract void deleteObjectById(String codArt);
	
//	public Iterable<T> SelTutti();
	
//	public List<T> SelByDescrizione(String descrizione, Pageable pageable);
	
//	public void DelArticolo(T articolo);
//	
//	public void InsArticolo(T articolo);
}
