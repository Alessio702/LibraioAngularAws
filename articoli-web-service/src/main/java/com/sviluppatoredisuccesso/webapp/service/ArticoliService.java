package com.sviluppatoredisuccesso.webapp.service;

import java.io.Serializable;
import java.util.List;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;

public interface ArticoliService<E extends Articoli, ID extends Serializable>
{
	public abstract List<Articoli> selectByDescrizione(String descrizione);
	
	public abstract List<E> selectByFilter(String filter);
	
	public abstract E selectByCodArt(String codArt);
	
	public abstract E selectById(String codArt);
	
	public abstract void addOrUpdate(E object);
	
	public abstract void deleteObject(E object);
	
	public abstract void deleteObjectById(String codArt);
	
//	public Iterable<T> SelTutti();
	
//	public List<T> SelByDescrizione(String descrizione, Pageable pageable);
	
//	public void DelArticolo(T articolo);
//	
//	public void InsArticolo(T articolo);
}