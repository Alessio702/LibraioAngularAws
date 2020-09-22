package com.xantrix.webapp.service;


//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xantrix.webapp.entity.DettListini;
import com.xantrix.webapp.repository.PrezziRepository;

@Service
//@Transactional
public class PrezziServiceImpl implements PrezziService
{
	@Autowired
	PrezziRepository prezziRepository;

//	@Autowired
//    private MongoTemplate mongoTemplate;
	
	//Metodi già presenti usati con l'annotation @Query
	@Override
	public DettListini SelPrezzo(String Id, String Listino)
	{
		return prezziRepository.SelByIdAndList(Id, Listino);
	}
	
	
	//Metodi senza l'annotation @Query
	@Override
	public DettListini selectPrezzo(String Id, String Listino)
	{
		return prezziRepository.SelByIdAndList(Id, Listino);
	}

	
	
	@Override
	public void DelPrezzo(String Id, String IdList) 
	{
		prezziRepository.DelRowDettList(Id, IdList);
	}
	
	@Override
	public void findAndDeletePrezzo(String Id, String IdList) 
	{
		prezziRepository.DelRowDettList(Id, IdList);
	}



	
	
	//Metodi con i criteria per MongoDB
//	@Override
//	public DettListini findPrezzo(String Id, String Listino){
//		Query query = Query.query(new Criteria().andOperator(
//				Criteria.where("dettListini.id").is(Id),
//				Criteria.where("listini.id").is(Listino)));
//		return (DettListini) mongoTemplate.find(query, DettListini.class);	
//	}
//	
//	@Override
//	public void removePrezzo(String Id, String IdList) 	{
//		Query query = Query.query(new Criteria().andOperator(
//				Criteria.where("dettListini.id").is(Id),
//				Criteria.where("dettListini.id").is(IdList)));
//		mongoTemplate.findAndRemove(query, DettListini.class);
//	}
	
	

}
