package com.sviluppatoredisuccesso.webapp.service;


import java.io.Serializable;

import java.util.List;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;


public interface ArticoliService<E extends Articoli, ID extends Serializable>
{
	
	public abstract List<E> selectByDescription(String filter);
	
	public abstract E selectByCodArt(Integer codArt);
	
	public abstract void addOrUpdate(E object);
	
	public abstract void deleteObject(E object);
	
	public abstract void deleteObjectById(Integer codArt);

	public abstract List<E> findAll();
	
}
