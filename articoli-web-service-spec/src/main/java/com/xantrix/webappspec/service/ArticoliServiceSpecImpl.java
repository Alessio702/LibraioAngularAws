package com.xantrix.webappspec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sviluppatoredisuccesso.webapp.service.ArticoliServiceImpl;
import com.xantrix.webappspec.entities.ArticoliSpec;
import com.xantrix.webappspec.repository.ArticoliRepositorySpec;

public class ArticoliServiceSpecImpl extends ArticoliServiceImpl implements ArticoliServiceSpec
{

	@Autowired
	ArticoliRepositorySpec articoliRepositorySpec;
	
	@Override
	public List<ArticoliSpec> getLibriByRedazione(String redazione) {
		return articoliRepositorySpec.getLibriFromRedazione(redazione);
	}
}
