package com.sviluppatoredisuccesso.webapp.service;

import java.io.Serializable;
import java.util.List;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;

//@Service
public interface ArticoliService<E extends Articoli, ID extends Serializable>
{
	public abstract List<Articoli> selectByDescrizione(String descrizione);
	
	public abstract List<E> selectByFilter(String filter);
	
	public abstract E selectById(ID codArt);
	
	public abstract String saveObject(E object);
	
	public abstract String updateObject(E object);
	
	public abstract void deleteObject(E object);
	
	public abstract void deleteObjectById(ID codArt);
	
//	public Iterable<T> SelTutti();
	
//	public List<T> SelByDescrizione(String descrizione, Pageable pageable);
	
//	public void DelArticolo(T articolo);
//	
//	public void InsArticolo(T articolo);
}
