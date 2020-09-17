package com.sviluppatoredisuccesso.webapp.service;


import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;
import com.sviluppatoredisuccesso.webapp.repository.ArticoliRepository;

@Service
@Transactional(readOnly = true)
public abstract class ArticoliServiceImpl<E extends Articoli, ID extends Serializable> implements ArticoliService<E, ID>
{

	@Autowired
	private ArticoliRepository<E> articoliRepository;
	


	@Override
	public List<E> selectByDescription(String filter) {
		return articoliRepository.selectByDescription(filter);
	}
	
	@Override
	public E selectByCodArt(Integer codArt) {
		return articoliRepository.selectByCodArt(codArt);
	}

	@Override
	public void addOrUpdate(E object) {
		articoliRepository.save(object);
	}
	
	@Override
	public void deleteObject(E object) {
		articoliRepository.delete(object);
	}

	@Override
	public void deleteObjectById(Integer codArt) {
		articoliRepository.deleteById(codArt);
	}
	
	
}
