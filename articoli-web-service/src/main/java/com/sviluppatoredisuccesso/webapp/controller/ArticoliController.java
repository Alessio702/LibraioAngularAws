package com.sviluppatoredisuccesso.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sviluppatoredisuccesso.webapp.entities.Articoli;
import com.sviluppatoredisuccesso.webapp.exception.NotFoundException;
import com.sviluppatoredisuccesso.webapp.service.ArticoliService;

@RestController
@CrossOrigin
@RequestMapping("api/articoli")
public class ArticoliController {
	private static final Logger logger = LoggerFactory.getLogger(ArticoliController.class);

	@Autowired
	private ArticoliService articoliService;


	@Autowired
	private PriceClient priceClient;

	// ------------------- Ricerca Per Codice ------------------------------------
		@GetMapping(value = "/cerca/codice/{codart}", produces = "application/json")
		public ResponseEntity<Articoli> listArtByCodArt(@PathVariable("codart") String CodArt, HttpServletRequest httpRequest) throws NotFoundException {
			
			logger.info("****** Otteniamo l'articolo con codice " + CodArt + " *******");
			String AuthHeader = httpRequest.getHeader("Authorization");
			Articoli articolo = articoliService.SelByCodArt(CodArt);
			if (articolo == null) {
				String ErrMsg = String.format("L'articolo con codice %s non è stato trovato!", CodArt);
				logger.warn(ErrMsg);
				throw new NotFoundException(ErrMsg);
			} else 
				articolo.setPrezzo(this.getPriceArt(articolo.getCodArt(), "", AuthHeader));
			return new ResponseEntity<Articoli>(articolo, HttpStatus.OK);
		}

		
		
		
		// ------------------- Ricerca Per Descrizione ------------------------------------
		@GetMapping(value = "/cerca/descrizione/{filter}", produces = "application/json")
		public ResponseEntity<List<Articoli>> listArtByDesc(@PathVariable("filter") String Filter, HttpServletRequest httpRequest) throws NotFoundException {
			
			logger.info("****** Otteniamo gli articoli con Descrizione: " + Filter + " *******");
			String AuthHeader = httpRequest.getHeader("Authorization");
			List<Articoli> articoli = articoliService.SelByDescrizione(Filter.toUpperCase() + "%");
			if (articoli == null) {
				String ErrMsg = String.format("Non è stato trovato alcun articolo avente descrizione %s", Filter);
				logger.warn(ErrMsg);
				throw new NotFoundException(ErrMsg);
			} else 
				articoli.forEach(f -> f.setPrezzo(this.getPriceArt(f.getCodArt(), "", AuthHeader)));
			return new ResponseEntity<List<Articoli>>(articoli, HttpStatus.OK);
		}
	
	
	
	
	private Double getPriceArt(String CodArt, String IdList, String Header) {

		Double Prezzo = (IdList.length() > 0) ? priceClient.getPriceArt(Header, CodArt, IdList) : priceClient.getDefPriceArt(Header, CodArt);
		logger.info("Prezzo Articolo " + CodArt + ": " + Prezzo);
		return Prezzo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Autowired
//	private BarcodeService barcodeService;
//	@Autowired
//	private ResourceBundleMessageSource errMessage;

//	// ------------------- Ricerca Per Barcode ------------------------------------
//	@GetMapping(value = "/cerca/ean/{barcode}", produces = "application/json")
//	public ResponseEntity<Articoli> listArtByEan(@PathVariable("barcode") String Barcode, HttpServletRequest httpRequest)
//				
//	{
//		logger.info("****** Otteniamo l'articolo con barcode " + Barcode + " *******");
//		
//		String AuthHeader = httpRequest.getHeader("Authorization");
//		
//		Articoli articolo;
//		Barcode Ean = barcodeService.SelByBarcode(Barcode);
//		
//		if (Ean == null)
//		{
//			String ErrMsg = String.format("Il barcode %s non è stato trovato!", Barcode);
//			
//			logger.warn(ErrMsg);
//			
//			throw new NotFoundException(ErrMsg);
//			//return new ResponseEntity<Articoli>(HttpStatus.NOT_FOUND);
//		}
//		else
//		{
//			articolo = Ean.getArticolo();
//			articolo.setPrezzo(this.getPriceArt(articolo.getCodArt(),"",AuthHeader));
//		}
//		
//		return new ResponseEntity<Articoli>(articolo, HttpStatus.OK);
//		
//	}
//	
//	// ------------------- INSERIMENTO ARTICOLO ------------------------------------
//	@PostMapping(value = "/inserisci", produces = "application/json")
//	public ResponseEntity<?> createArt(@Valid @RequestBody Articoli articolo, BindingResult bindingResult)
//		throws BindingException, DuplicateException
//	{
//		logger.info("Salviamo l'articolo con codice " + articolo.getCodArt());
//		
//		//controllo validità dati articolo
//		if (bindingResult.hasErrors())
//		{
//			String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());
//			
//			logger.warn(MsgErr);
//			
//			throw new BindingException(MsgErr);
//		}
//		
//		//Disabilitare se si vuole gestire anche la modifica 
//		Articoli checkArt =  articoliService.SelByCodArt(articolo.getCodArt());
//		
//		if (checkArt != null)
//		{
//			String MsgErr = String.format("Articolo %s presente in anagrafica! "
//					+ "Impossibile utilizzare il metodo POST", articolo.getCodArt());
//			
//			logger.warn(MsgErr);
//			
//			throw new DuplicateException(MsgErr);
//		}
//		
//		articoliService.InsArticolo(articolo);
//		
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectNode responseNode = mapper.createObjectNode();
//		
//		responseNode.put("code", HttpStatus.OK.toString());
//		responseNode.put("message", "Inserimento Articolo " + articolo.getCodArt() + " Eseguita Con Successo");
//		
//		return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.CREATED);
//	}
//	
//	// ------------------- MODIFICA ARTICOLO ------------------------------------
//	@RequestMapping(value = "/modifica", method = RequestMethod.PUT, produces = "application/json")
//	public ResponseEntity<?> updateArt(@Valid @RequestBody Articoli articolo, BindingResult bindingResult)
//			throws BindingException,NotFoundException 
//	{
//		logger.info("Modifichiamo l'articolo con codice " + articolo.getCodArt());
//		
//		if (bindingResult.hasErrors())
//		{
//			String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());
//			
//			logger.warn(MsgErr);
//
//			throw new BindingException(MsgErr);
//		}
//		
//		Articoli checkArt =  articoliService.SelByCodArt(articolo.getCodArt());
//
//		if (checkArt == null)
//		{
//			String MsgErr = String.format("Articolo %s non presente in anagrafica! "
//					+ "Impossibile utilizzare il metodo PUT", articolo.getCodArt());
//			
//			logger.warn(MsgErr);
//			
//			throw new NotFoundException(MsgErr);
//		}
//		
//		articoliService.InsArticolo(articolo);
//		
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectNode responseNode = mapper.createObjectNode();
//		
//		responseNode.put("code", HttpStatus.OK.toString());
//		responseNode.put("message", "Modifica Articolo " + articolo.getCodArt() + " Eseguita Con Successo");
//		
//		return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.CREATED);
//	}
//	
//	// ------------------- ELIMINAZIONE ARTICOLO ------------------------------------
//	@RequestMapping(value = "/elimina/{codart}", method = RequestMethod.DELETE, produces = "application/json" )
//	public ResponseEntity<?> deleteArt(@PathVariable("codart") String CodArt)
//		 
//	{
//		logger.info("Eliminiamo l'articolo con codice " + CodArt);
//		
//		Articoli articolo = articoliService.SelByCodArt(CodArt);
//		
//		if (articolo == null)
//		{
//			String MsgErr = String.format("Articolo %s non presente in anagrafica!",CodArt);
//			
//			logger.warn(MsgErr);
//			
//			throw new NotFoundException(MsgErr);
//		}
//		
//		articoliService.DelArticolo(articolo);
//		
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectNode responseNode = mapper.createObjectNode();
//		
//		responseNode.put("code", HttpStatus.OK.toString());
//		responseNode.put("message", "Eliminazione Articolo " + CodArt + " Eseguita Con Successo");
//		
//		return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);
//				
//	}

}
