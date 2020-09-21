package com.sviluppatoredisuccesso.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;

import feign.Param;



public interface ArticoliRepository<E extends Articoli> extends JpaRepository<E, Integer> 
{
	@Query(value = "SELECT t FROM Articoli t WHERE t.descrizione LIKE ?1%")
	List<E> selectByDescription(@Param("filter") String filter);
	
	
	@Query(value = "SELECT t FROM Articoli t WHERE t.id = ?1")
	E selectById(Integer id);
	
	
	
}
