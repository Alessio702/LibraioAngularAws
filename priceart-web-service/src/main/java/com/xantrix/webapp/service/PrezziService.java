package com.xantrix.webapp.service;

import com.xantrix.webapp.entity.DettListini;
 
public interface PrezziService
{
	public DettListini SelPrezzo(String Id, String Listino);
	

	public void DelPrezzo(String CodArt, String IdList);

}
