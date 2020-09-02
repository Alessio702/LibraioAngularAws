package com.sviluppatoredisuccesso.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;

public interface ArticoliRepository<E>  extends PagingAndSortingRepository<Articoli, String>
{
	@Query(value = "SELECT * FROM ARTICOLI WHERE DESCRIZIONE LIKE :desArt", nativeQuery = true)
	List<Articoli> SelByDescrizioneLike(@Param("desArt") String descrizione);

	Articoli findByCodArt(String codArt);

	
	@Query(value = "SELECT * FROM " + /* E + */ " WHERE " + /* filter + */ " LIKE :filter", nativeQuery = true)
	List<E> selectByTypeAndFilter(@Param("tipoOggetto") E tipoOggetto, @Param("filter") String filter);
	
	
//	@Query(value = "SELECT * FROM LIBRO WHERE " + filter + " LIKE :filter", nativeQuery = true)
//	List<Object> SelByFilterLike(@Param("filter") String filter);
	
	//	List<Articoli> findByDescrizioneLike(String descrizione, Pageable pageable);
}
