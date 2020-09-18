package com.sviluppatoredisuccesso.webapp.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sviluppatoredisuccesso.webapp.dto.ArticoliDto;
import com.sviluppatoredisuccesso.webapp.entities.Articoli;
import com.sviluppatoredisuccesso.webapp.exception.BindingException;
import com.sviluppatoredisuccesso.webapp.exception.DuplicateException;
import com.sviluppatoredisuccesso.webapp.exception.NotFoundException;
import com.sviluppatoredisuccesso.webapp.service.ArticoliService;

@RestController
@CrossOrigin
@RequestMapping("api/articoli")
public class ArticoliController<E extends Articoli, G extends ArticoliDto, ID extends Serializable> {

	private static final Logger logger = LoggerFactory.getLogger(ArticoliController.class);

	@Autowired
	private PriceClient priceClient;

	@Autowired
	private ArticoliService<E, ID> articoliService;

	@Autowired
	private ResourceBundleMessageSource errMessage;

	

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/cerca/codice/{codArt}", produces = "application/json")
	public ResponseEntity<G> genericSearchByCodArt(@PathVariable("codArt") String codArt, HttpServletRequest httpRequest) throws NotFoundException {

		logger.info("****** Ricerca di filtrata per codice: " + codArt + "!");
		G dtoObject = null;
		
		if(codArt.matches("[0-9]+")) {
			Integer codConverted = Integer.valueOf(codArt);
			E articolo = articoliService.selectByCodArt(codConverted);
			
			if (articolo == null) {
				String errMsg = String.format("Non è stato trovato alcun articolo con codice: ", codArt);
				logger.warn(errMsg);
				
				throw new NotFoundException(errMsg);
			} else {
				String authHeader = httpRequest.getHeader("Authorization");
				dtoObject = (G) articolo.convertArticoliToDTO();
				
				dtoObject.setPrezzo(this.getPriceArt(codArt, "", authHeader));
				return new ResponseEntity<G>(dtoObject, HttpStatus.OK);
			}
		} else {
			throw new NotFoundException("Inserito un codice alfanumerico invece che numerico");
		}
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/cerca/descrizione/{description}", produces = "application/json")
	public ResponseEntity<List<G>> genericSearchByDescription(@PathVariable("description") String description, HttpServletRequest httpRequest) throws NotFoundException {

		logger.info("****** Ricerca di filtrata per filtro: " + description + "!");

		List<E> searchList = articoliService.selectByDescription(description);
		List<G> listDto = new ArrayList<G>();

		if (searchList.size() == 0) {
			String errMsg = String.format("Non è stato trovato alcun oggetto con filtro %s", description);
			logger.warn(errMsg);

			throw new NotFoundException(errMsg);
		} else {
			String authHeader = httpRequest.getHeader("Authorization");
			
			for(int i = 0; i < searchList.size(); i++) {
				E articolo = searchList.get(i);
				G dtoObject = (G) articolo.convertArticoliToDTO();
				
				listDto.add(dtoObject); 
			}

			searchList.forEach(f -> f.setPrezzo(this.getPriceArt(f.getCodArt().toString(), "", authHeader)));
		}

		return new ResponseEntity<List<G>>(listDto, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/inserisci", produces = "application/json")
	public ResponseEntity<?> genericAddObject(@Valid @RequestBody E object, HttpServletRequest httpRequest, BindingResult bindingResult) throws BindingException, DuplicateException {

		logger.info("****** inserimento record ******");

		if (bindingResult.hasErrors()) {
			String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());
			logger.warn(MsgErr);

			throw new BindingException(MsgErr);
		}

		HttpHeaders headers = new HttpHeaders();
		ObjectMapper mapper = new ObjectMapper();

		headers.setContentType(MediaType.APPLICATION_JSON);
		ObjectNode responseNode = mapper.createObjectNode();

		articoliService.addOrUpdate(object);

		responseNode.put("code", HttpStatus.OK.toString());
		responseNode.put("message", "Inserimento Articolo eseguito Con Successo");

		return new ResponseEntity<>(responseNode, headers, HttpStatus.CREATED);
	}


	@GetMapping(value = "/elimina/codice/{codart}", produces = "application/json")
	public ResponseEntity<?> genericDeleteByCodArt(@PathVariable("codart") Integer codart) throws NotFoundException {
		
		logger.info("Eliminiamo l'articolo con codice " + codart);

		HttpHeaders headers = new HttpHeaders();
		ObjectMapper mapper = new ObjectMapper();

		headers.setContentType(MediaType.APPLICATION_JSON);
		ObjectNode responseNode = mapper.createObjectNode();
		
		E object = articoliService.selectByCodArt(codart);
		
		if (object != null) {
			articoliService.deleteObjectById(codart);
			responseNode.put("message", "Eliminazione Articolo " + codart + " Eseguita Con Successo");
		} else {
			responseNode.put("message", "Eliminazione Articolo " + codart + " non eseguita!");
		}
		
		responseNode.put("code", HttpStatus.OK.toString());
		
		return new ResponseEntity<>(responseNode, headers, HttpStatus.OK);
	}

	private Double getPriceArt(String CodArt, String IdList, String Header) {
		try {
			Double Prezzo = (IdList.length() > 0) ? priceClient.getPriceArt(Header, CodArt, IdList)
					: priceClient.getDefPriceArt(Header, CodArt);
			logger.info("Prezzo Articolo " + CodArt + ": " + Prezzo);

			return Prezzo;
		} catch (Exception ex) {
			logger.error("Errore ottenimento prezzo: " + ex.getMessage());
			return 0.0;
		}
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
//	------------------- INSERIMENTO ARTICOLO ------------------------------------
//	@PostMapping(value = "/inserisci", produces = "application/json")
//	public ResponseEntity<?> createArt(@Valid @RequestBody Articoli articolo, BindingResult bindingResult)
//			throws BindingException, DuplicateException
//	{
//		logger.info("Salviamo l'articolo con codice " + articolo.getCodArt());
//
//		controllo validità dati articolo
//		if (bindingResult.hasErrors())
//		{
//			String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());
//
//			logger.warn(MsgErr);
//
//			throw new BindingException(MsgErr);
//		}
//
//		Disabilitare se si vuole gestire anche la modifica 
//		Articoli checkArt =  articoliService.SelByCodArt(articolo.getCodArt());
//

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
//	------------------- MODIFICA ARTICOLO ------------------------------------

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
//	------------------- ELIMINAZIONE ARTICOLO ------------------------------------
//	@RequestMapping(value = "/elimina/{codart}", method = RequestMethod.DELETE, produces = "application/json" )
//	public ResponseEntity<?> deleteArt(@PathVariable("codart") Integer CodArt)
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
//	public ResponseEntity<?> deleteArt(@PathVariable("codart") Integer CodArt)
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
