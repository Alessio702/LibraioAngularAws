package com.xantrix.webapp.service;


//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.xantrix.webapp.entity.DettListini;
import com.xantrix.webapp.repository.PrezziRepository;

@Service
//@Transactional
public class PrezziServiceImpl implements PrezziService
{
	@Autowired
	PrezziRepository prezziRepository;

	@Autowired
    private MongoTemplate mongoTemplate;
	
	//Metodi già presenti usati con l'annotation @Query
	@Override
	public DettListini SelPrezzo(String CodArt, String Listino)
	{
		return prezziRepository.SelByCodArtAndList(CodArt, Listino);
	}
	
	
	//Metodi senza l'annotation @Query
	@Override
	public DettListini selectPrezzo(String CodArt, String Listino)
	{
		return prezziRepository.SelByCodArtAndList(CodArt, Listino);
	}

	
	
	@Override
	public void DelPrezzo(String CodArt, String IdList) 
	{
		prezziRepository.DelRowDettList(CodArt, IdList);
	}
	
	@Override
	public void findAndDeletePrezzo(String CodArt, String IdList) 
	{
		prezziRepository.DelRowDettList(CodArt, IdList);
	}



	
	
	//Metodi con i criteria per MongoDB
//	@Override
//	public DettListini findPrezzo(String CodArt, String Listino){
//		Query query = Query.query(new Criteria().andOperator(
//				Criteria.where("dettListini.codArt").is(CodArt),
//				Criteria.where("listini.id").is(Listino)));
//		return (DettListini) mongoTemplate.find(query, DettListini.class);	
//	}
//	
//	@Override
//	public void removePrezzo(String CodArt, String IdList) 	{
//		Query query = Query.query(new Criteria().andOperator(
//				Criteria.where("dettListini.codArt").is(CodArt),
//				Criteria.where("dettListini.id").is(IdList)));
//		mongoTemplate.findAndRemove(query, DettListini.class);
//	}
	
	

}
