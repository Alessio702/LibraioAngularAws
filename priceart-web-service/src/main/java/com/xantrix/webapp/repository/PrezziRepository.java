package com.xantrix.webapp.repository;




//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
public interface PrezziRepository extends CrudRepository<DettListini, Long> {

	//@Query("{ 'codart' : ?0 }, {'idList : ?1'}")
	DettListini DelRowDettList(@Param("codart") String CodArt, @Param("idlist") String IdList);

	//@Query("{ 'codart' : ?0 }, {'idList' : ?1}")
	DettListini SelByCodArtAndList(@Param("codart") String CodArt, @Param("idlist") String Listino);

}
