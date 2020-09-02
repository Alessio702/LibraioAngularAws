package com.sviluppatoredisuccesso.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sviluppatoredisuccesso.webapp.repository.ArticoliRepository;

@Service
@Transactional(readOnly = true)
public class ArticoliServiceGeneralImpl<E> implements ArticoliServiceGeneral<E>
{
	@Autowired
	ArticoliRepository<E> articoliRepository;

	@Override
	public List<Object> SelectByRedazione(String redazione) {
//		return articoliRepository.SelByRedazioneLike(redazione);
		return null;
	}

	@Override
	public List<E> SelectByFilter(E tipoOggetto, String filter) {
		return articoliRepository.selectByTypeAndFilter(tipoOggetto, filter);
	}
	

	
}
