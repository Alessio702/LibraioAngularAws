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
	public Articoli selectByCodArt(String codArt) {
		return articoliRepository.findByCodArt(codArt);
	}

	@Override
	public List<E> selectByFilter(String oggetto, String filter) {
		return articoliRepository.selectByObjectAndFilter(oggetto, filter);
	}

	@Override
	public List<Articoli> selectByDescrizione(String descrizione) {
		return articoliRepository.SelByDescrizioneLike(descrizione);
	}
	

	
}
