package com.xantrix.webapp.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.xantrix.webapp.entity.Listini;


@Repository
public interface ListinoRepository extends MongoRepository<Listini, String> {
	
}
