package com.xantrix.webappspec.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import com.sviluppatoredisuccesso.webapp.controller.ArticoliController;
import com.sviluppatoredisuccesso.webapp.exception.NotFoundException;
import com.xantrix.webappspec.entities.ArticoliSpec;
import com.xantrix.webappspec.service.ArticoliServiceSpec;

public class ArticoliControllerSpec<E> extends ArticoliController<ArticoliSpec> {
	
	public ArticoliControllerSpec(ArticoliSpec tipoArticolo) {
		super(tipoArticolo);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	ArticoliServiceSpec articoliServiceSpec;
	
	
	public /* ResponseEntity<List<ArticoliSpec>>*/void  getLibriByRedazione(@PathVariable("redazione") String redazione, HttpServletRequest httpRequest) throws NotFoundException {
		
		this.genericSearchByTypeAndFilter(new ArticoliSpec(), redazione);		
		
		
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
