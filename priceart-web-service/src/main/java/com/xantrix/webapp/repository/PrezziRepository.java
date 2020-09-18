package com.xantrix.webapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.PathVariable;

import com.xantrix.webapp.entity.DettListini;

/*
public interface PrezziRepository extends JpaRepository<DettListini, Long>
{
	@Modifying
	@Query(value = "DELETE FROM dettlistini WHERE CodArt = :codart AND IdList = :idlist", nativeQuery = true)
	void DelRowDettList(@Param("codart") String CodArt, @Param("idlist") String IdList);
	
	//Query JPQL
	@Query(value = "SELECT b FROM Listini a JOIN a.dettListini b WHERE b.codArt = :codart AND a.id = :idlist")
	DettListini SelByCodArtAndList(@Param("codart") String CodArt, @Param("idlist") String Listino);
}*/

@Repository
public interface PrezziRepository extends MongoRepository<DettListini, Long> {

	@Query("{ 'codart' : ?0 }, {'idList : ?1'}")
	DettListini DelRowDettList(String CodArt, String IdList);

	@Query("{ 'codart' : ?0 }, {'idList' : ?1}")
	DettListini SelByCodArtAndList(String CodArt, String Listino);
	
	
//	//metodi senza annotation
//	DettListini findAndDeleteDettList(String CodArt, String IdList);
//	
//	DettListini findByCodArtAndList(String CodArt, String Listino);
//	
	
	
	


}
