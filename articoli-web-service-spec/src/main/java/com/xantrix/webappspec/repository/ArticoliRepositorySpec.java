package com.xantrix.webappspec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sviluppatoredisuccesso.webapp.repository.ArticoliRepository;
import com.xantrix.webappspec.entities.ArticoliSpec;

public interface ArticoliRepositorySpec extends ArticoliRepository {
	
	// Da rivedere il nome dei campi e della tabella libro
	@Query(value = "SELECT * FROM LIBRO WHERE REDAZIONE LIKE :redazione", nativeQuery = true)
	List<ArticoliSpec> getLibriFromRedazione(@Param("redazione") String redazione);
	
}
