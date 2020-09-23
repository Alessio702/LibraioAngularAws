package com.xantrix.webapp.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xantrix.webapp.entity.DettListini;
import com.xantrix.webapp.repository.PrezziRepository;

@Service
public class PrezziServiceImpl implements PrezziService
{
	@Autowired
	PrezziRepository prezziRepository;
	

	@Override
	public DettListini SelPrezzo(String Id, String Listino)
	{

		return prezziRepository.SelByCodArtAndList(Id, Listino);
	}
	
	@Override
	public void DelPrezzo(String Id, String IdList) 
	{
		prezziRepository.DelRowDettList(Id, IdList);
	}


	
}
