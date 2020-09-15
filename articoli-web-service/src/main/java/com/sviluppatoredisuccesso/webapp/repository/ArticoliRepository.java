package com.sviluppatoredisuccesso.webapp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;



public interface ArticoliRepository<E extends Articoli> extends JpaRepository<E, String> 
{
	@Query(value = "SELECT * FROM ARTICOLI2 WHERE DESCRIZIONE LIKE :desArt", nativeQuery = true)
	List<Articoli> selByDescrizioneLike(@Param("desArt") String descrizione);

//	Articoli findByCodArt(String codArt);

	
	@Query(value = "SELECT t FROM ARTICOLI2 t WHERE t.descrizione LIKE :filter")
	List<E> selectByFilter(@Param("filter") String filter);
	
	@Query(value = "SELECT t FROM ARTICOLI2 t WHERE t.codArt LIKE :codArt")
	E selectByCodArt(@Param("codArt") String codArt);
	
	
//	@Query(value = "SELECT * FROM LIBRO WHERE " + filter + " LIKE :filter", nativeQuery = true)
//	List<Object> SelByFilterLike(@Param("filter") String filter);

}
