package com.xantrix.webappspec.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sviluppatoredisuccesso.webapp.service.ArticoliServiceImpl;
import com.xantrix.webappspec.entities.Libro;

@Service
@Transactional(readOnly = true)
public class ArticoliServiceSpecImpl extends ArticoliServiceImpl<Libro, Integer> implements ArticoliServiceSpec
{

	
	
//	public ArticoliServiceSpecImpl(ArticoliRepository<ArticoliSpec> articoliRepository) {
//		super(articoliRepository);
//	}
//	
}
