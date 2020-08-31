package com.sviluppatoredisuccesso.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;
import com.sviluppatoredisuccesso.webapp.repository.ArticoliRepository;

@Service
@Transactional(readOnly = true)
public class ArticoliServiceImpl implements ArticoliService
{
	@Autowired
	ArticoliRepository articoliRepository;
	

	@Override
	public List<Articoli> SelByDescrizione(String descrizione)
	{
		return articoliRepository.SelByDescrizioneLike(descrizione);
	}
	
	@Override
	public Articoli SelByCodArt(String codArt)
	{
		return articoliRepository.findByCodArt(codArt);
	}

	
	
	
	
	
//	@Override
//	public Iterable<Articoli> SelTutti()
//	{
//		return articoliRepository.findAll();
//	}
	
//	@Override
//	public List<Articoli> SelByDescrizione(String descrizione, Pageable pageable)
//	{
//		return articoliRepository.findByDescrizioneLike(descrizione, pageable);
//	}
//
//	@Override
//	@Transactional
//	public void DelArticolo(Articoli articolo)
//	{
//		articoliRepository.delete(articolo);
//	}
//
//	@Override
//	@Transactional
//	public void InsArticolo(Articoli articolo)
//	{
//		articoliRepository.save(articolo);
//	}
}
