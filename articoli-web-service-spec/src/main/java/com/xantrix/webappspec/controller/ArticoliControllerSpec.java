package com.xantrix.webappspec.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sviluppatoredisuccesso.webapp.controller.ArticoliController;
import com.sviluppatoredisuccesso.webapp.exception.NotFoundException;
import com.xantrix.webappspec.entities.ArticoliSpec;
import com.xantrix.webappspec.service.ArticoliServiceSpec;

public class ArticoliControllerSpec<T> extends ArticoliController<ArticoliSpec> {
	
	public ArticoliControllerSpec(ArticoliSpec tipoArticolo) {
		super(tipoArticolo);
	}
	
	@Autowired
	ArticoliServiceSpec articoliServiceSpec;
	
	// ricerca libri
	@GetMapping(value = "/cerca/libri/{redazione}", produces = "application/json")
	public ResponseEntity<List<ArticoliSpec>> getLibriByRedazione(@PathVariable("redazione") String redazione, HttpServletRequest httpRequest) throws NotFoundException {
		
		@SuppressWarnings("unchecked")
		List<ArticoliSpec> listaLibri = (List<ArticoliSpec>) this.getLibriByRedazioneTipoContenuto(redazione, httpRequest);
				
		
		return new ResponseEntity<List<ArticoliSpec>>(listaLibri, HttpStatus.OK);
		
		
		
		
		
		
		
		
//		logger.info("****** Otteniamo i libri con redazione: " + redazione + " *******");
//		String AuthHeader = httpRequest.getHeader("Authorization");
//		
//		List<ArticoliSpec> libri = articoliServiceSpec.getLibriByRedazione(redazione);
//		
//		if (libri.size() == 0) {
//			String ErrMsg = String.format("Non è stato trovato alcun libro avente redazione %s", redazione);
//			logger.warn(ErrMsg);
//			throw new NotFoundException(ErrMsg);
//		} else {
//			
//		}
		
//		return new ResponseEntity<List<ArticoliSpec>>(libri, HttpStatus.OK);
	}
}
