package com.xantrix.webappspec.controller;

import com.sviluppatoredisuccesso.webapp.controller.ArticoliController;
import com.xantrix.webappspec.entities.ArticoliSpec;

public class ArticoliControllerSpec extends ArticoliController<ArticoliSpec> {
	
	public ArticoliControllerSpec(ArticoliSpec tipoArticolo) {
		super(tipoArticolo);
		// TODO Auto-generated constructor stub
	}

//	@Autowired
//	ArticoliServiceSpec<E> articoliServiceSpec;
//	
	
//	@GetMapping(value = "/cerca/{oggetto}/{filter}", produces = "application/json")
//	public void  getLibriByRedazione(@PathVariable("oggetto") ArticoliSpec oggetto, @PathVariable("filter") String filter, HttpServletRequest httpRequest) throws NotFoundException {
//		
//		this.genericSearchByTypeAndFilter(oggetto, filter);
//	}
}
