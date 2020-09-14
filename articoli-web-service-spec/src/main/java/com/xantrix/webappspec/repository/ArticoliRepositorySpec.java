package com.xantrix.webappspec.repository;

import org.springframework.data.repository.NoRepositoryBean;

import com.sviluppatoredisuccesso.webapp.repository.ArticoliRepository;
import com.xantrix.webappspec.entities.ArticoliSpec;

@NoRepositoryBean
public interface ArticoliRepositorySpec extends ArticoliRepository<ArticoliSpec> {
	

}
