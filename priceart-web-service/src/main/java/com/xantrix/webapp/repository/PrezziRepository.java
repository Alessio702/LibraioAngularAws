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
	@Query(value = "DELETE FROM dettlistini WHERE Id = :id AND IdList = :idlist", nativeQuery = true)
	void DelRowDettList(@Param("id") String Id, @Param("idlist") String IdList);
	
	//Query JPQL
	@Query(value = "SELECT b FROM Listini a JOIN a.dettListini b WHERE b.Id = :id AND a.id = :idlist")
	DettListini SelByIdAndList(@Param("id") String Id, @Param("idlist") String Listino);
}*/

@Repository
public interface PrezziRepository extends MongoRepository<DettListini, Long> {

	@Query("{ 'id' : ?0 }, {'idList : ?1'}")
	DettListini DelRowDettList(String Id, String IdList);

	@Query("{ 'id' : ?0 }, {'idList' : ?1}")
	DettListini SelByIdAndList(String Id, String Listino);
	
	
//	//metodi senza annotation
//	DettListini findAndDeleteDettList(String Id, String IdList);
//	
//	DettListini findByIdAndList(String Id, String Listino);
//	
	
	
	


}
