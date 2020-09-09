package com.sviluppatoredisuccesso.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;
import com.sviluppatoredisuccesso.webapp.repository.ArticoliRepository;

@Service
@Transactional(readOnly = true)
public class ArticoliServiceImpl<E> implements ArticoliService<E>
{
	@Autowired
	ArticoliRepository<E> articoliRepository;


	@Override
	public List<E> selectByFilter(String filter) {
		return articoliRepository.selectByObjectAndFilter(filter);
	}

	@Override
	public List<Articoli> selectByDescrizione(String descrizione) {
		return articoliRepository.SelByDescrizioneLike(descrizione);
	}
	
	@Override
	public E selectById(String codArt) {
		return articoliRepository.findById(codArt).get();
	}

	@Override
	public E saveObject(E object) {
		return articoliRepository.save(object);
//		return null;
	}

	@Override
	public void deleteObject(E object) {
		articoliRepository.delete(object);
	}

	@Override
	public void deleteObjectById(String codArt) {
		articoliRepository.deleteById(codArt);
	}

	

	
	

	
}
