package com.xantrix.webappspec.repository;

import com.sviluppatoredisuccesso.webapp.repository.AbstractRepository;
import com.xantrix.webappspec.entities.ArticoliSpec;

public abstract class AbstractRepositorySpec extends AbstractRepository<ArticoliSpec, String> {

	public AbstractRepositorySpec(Class<ArticoliSpec> e) {
		super(e);
	}	
}
