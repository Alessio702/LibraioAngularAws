package com.sviluppatoredisuccesso.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sviluppatoredisuccesso.webapp.repository.ArticoliRepository;

@Service
@Transactional(readOnly = true)
public class ArticoliServiceGeneralImpl implements ArticoliServiceGeneral
{
	@Autowired
	ArticoliRepository articoliRepository;

	@Override
	public List<Object> SelectByRedazione(String redazione) {
		return articoliRepository.SelByRedazioneLike(redazione);
	}

	@Override
	public Object SelectByFilter(String filter) {
		return null;
	}
	

	
}
