package com.sviluppatoredisuccesso.webapp.service;


import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;
import com.sviluppatoredisuccesso.webapp.repository.ArticoliRepository;

@Service
public abstract class ArticoliServiceImpl<E extends Articoli, ID extends Serializable> implements ArticoliService<E, ID>
{

	@Autowired
	private ArticoliRepository<E> articoliRepository;
	


	@Override
	public List<E> selectByDescription(String filter) {
		return articoliRepository.selectByDescription(filter);
	}
	
	@Override
	public E selectById(Integer id) {
		return articoliRepository.selectById(id);
	}

	@Override
	public void addOrUpdate(E object) {
		articoliRepository.save(object);
	}
	
	@Override
	public List<E> findAll() {
		return articoliRepository.findAll();
	}
	
	@Override
	public void deleteObject(E object) {
		articoliRepository.delete(object);
	}

	@Override
	public void deleteObjectById(Integer id) {
		articoliRepository.deleteById(id);
	}
	
	
}
