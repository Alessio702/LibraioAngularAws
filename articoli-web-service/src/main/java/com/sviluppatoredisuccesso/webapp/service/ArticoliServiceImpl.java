package com.sviluppatoredisuccesso.webapp.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;
import com.sviluppatoredisuccesso.webapp.repository.AbstractRepository;

@Service
@Transactional(readOnly = true)
public abstract class ArticoliServiceImpl<E extends Articoli, ID extends Serializable> implements ArticoliService<E, ID>
{
//	@Autowired
//	private ArticoliRepository<E> articoliRepository;

	@Autowired
	private AbstractRepository<E, ID> abstractRepository;
	
	// Implementare metodi
	
	
	
	@Override
	public List<Articoli> selectByDescrizione(String descrizione) {
		return null;
	}

	@Override
	public List<E> selectByFilter(String filter) {
		return null;
	}

	@Override
	public E selectById(ID codArt) {
		return abstractRepository.search(codArt);
	}

	@Override
	public String saveObject(E object) {
		abstractRepository.save(object);
		
		return "Articolo '" + object.getDescrizione() + "' salvato correttamente!";
	}
	
	@Override
	public String updateObject(E object) {
		abstractRepository.update(object);
		
		return "Articolo '" + object.getDescrizione() + "' aggiornato correttamente!";
	}

	@Override
	public void deleteObject(E object) {
		abstractRepository.delete(object);
	}

	@Override
	public void deleteObjectById(ID codArt) {
		
	}
	
	

	
	
	
//	@Override
//	public List<E> selectByFilter(String filter) {
//		return articoliRepository.selectByObjectAndFilter(filter);
//	}
//
//	@Override
//	public List<Articoli> selectByDescrizione(String descrizione) {
//		return articoliRepository.SelByDescrizioneLike(descrizione);
//	}
//	
//	@Override
//	public E selectById(String codArt) {
//		return articoliRepository.findById(codArt).get();
//	}
//
//	@Override
//	public E saveObject(E object) {
//		return articoliRepository.save(object);
//	}
//
//	@Override
//	public void deleteObject(E object) {
//		articoliRepository.delete(object);
//	}
//
//	@Override
//	public void deleteObjectById(String codArt) {
//		articoliRepository.deleteById(codArt);
//	}

	

	
	

	
}
