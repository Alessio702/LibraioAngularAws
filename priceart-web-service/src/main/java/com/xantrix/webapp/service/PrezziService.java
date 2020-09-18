package com.xantrix.webapp.service;

import com.xantrix.webapp.entity.DettListini;
 
public interface PrezziService
{
	public DettListini SelPrezzo(String CodArt, String Listino);
	
	public void DelPrezzo(String CodArt, String IdList);
	
	//metodi senza annotation 
	public DettListini selectPrezzo(String CodArt, String Listino);
	
	public void findAndDeletePrezzo(String CodArt, String IdList);
	
	
	//usati con i criteria
//	public DettListini findPrezzo(String CodArt, String Listino);
//	
//	public void removePrezzo(String CodArt, String IdList);

}
