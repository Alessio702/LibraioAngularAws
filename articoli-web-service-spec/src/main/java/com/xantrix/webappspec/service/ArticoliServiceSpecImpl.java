package com.xantrix.webappspec.service;

import org.springframework.stereotype.Service;

import com.sviluppatoredisuccesso.webapp.repository.ArticoliRepository;
import com.sviluppatoredisuccesso.webapp.service.ArticoliServiceImpl;
import com.xantrix.webappspec.entities.ArticoliSpec;

@Service
public class ArticoliServiceSpecImpl extends ArticoliServiceImpl<ArticoliSpec, String> implements ArticoliServiceSpec
{
	//	@Autowired
	//	private AbstractRepository<ArticoliSpec, String> abstractRepository;
	
	public ArticoliServiceSpecImpl(ArticoliRepository<ArticoliSpec> articoliRepository) {
		super(articoliRepository);
	}
	

	
}
