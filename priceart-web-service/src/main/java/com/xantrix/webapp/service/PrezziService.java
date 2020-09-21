package com.xantrix.webapp.service;

import com.xantrix.webapp.entity.DettListini;
 
public interface PrezziService
{
	public DettListini SelPrezzo(String Id, String Listino);
	
	public void DelPrezzo(String Id, String IdList);
	
	//metodi senza annotation 
	public DettListini selectPrezzo(String Id, String Listino);
	
	public void findAndDeletePrezzo(String Id, String IdList);
	
	
	//usati con i criteria
//	public DettListini findPrezzo(String Id, String Listino);
//	
//	public void removePrezzo(String Id, String IdList);

}
