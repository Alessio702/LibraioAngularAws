package com.sviluppatoredisuccesso.webapp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sviluppatoredisuccesso.webapp.entities.Articoli;
import com.sviluppatoredisuccesso.webapp.exception.BindingException;
import com.sviluppatoredisuccesso.webapp.exception.NotFoundException;
import com.sviluppatoredisuccesso.webapp.service.ArticoliService;

@RestController
@CrossOrigin
@RequestMapping("api/articoli")
public class ArticoliController<E extends Articoli, ID extends Serializable> {
	
	private static final Logger logger = LoggerFactory.getLogger(ArticoliController.class);


	@Autowired
	private PriceClient priceClient;
	
	@Autowired
	private ArticoliService<E, ID> articoliService;
	
	@Autowired
	private ResourceBundleMessageSource errMessage;

	
	
	

	// ------------------- Ricerca Per Descrizione
	// ------------------------------------
	@GetMapping(value = "/cerca/descrizione/{filter}", produces = "application/json")
	public ResponseEntity<List<Articoli>> genericSearchByTypeAndFilter (@PathVariable("filter") String filter, HttpServletRequest httpRequest) throws NotFoundException {

		logger.info("****** filtrato per " + filter + "!");
		String AuthHeader = httpRequest.getHeader("Authorization");

		List<Articoli> searchList = new ArrayList<Articoli>();
		List<E> genericListByFilter = articoliService.selectByFilter(filter);
		
		for (int i = 0; i < genericListByFilter.size(); i++) {
			searchList.add((Articoli) genericListByFilter.get(i));
		}
		
		if (searchList.size() == 0) {
			String ErrMsg = String.format("Non è stato trovato alcun oggetto con filtro %s", filter);
			logger.warn(ErrMsg);
			
			throw new NotFoundException(ErrMsg);
		} else {
			searchList.forEach(f -> f.setPrezzo(this.getPriceArt(f.getCodArt(), "", AuthHeader)));
			return new ResponseEntity<List<Articoli>>(searchList, HttpStatus.OK);
		}
	}


	private Double getPriceArt(String CodArt, String IdList, String Header)
	{
		try {
			Double Prezzo = (IdList.length() > 0) ? priceClient.getPriceArt(Header, CodArt, IdList) : 
				priceClient.getDefPriceArt(Header, CodArt);
				logger.info("Prezzo Articolo " + CodArt + ": " + Prezzo);

				return Prezzo;
		} catch(Exception ex) {
			logger.error("Errore ottenimento prezzo: " + ex.getMessage());
			return 0.0;
		}
	}
	
	
	@GetMapping(value = "/cerca/{filter}", produces = "application/json")
	public ResponseEntity<List<E>> genericSearchByFilter(@PathVariable("filter") String filter) throws NotFoundException {

		logger.info("****** ricerca di filtrato per " + filter + "!");
//		System.out.println(this.tipoArticolo.getClass().getName());
//		System.out.println(this.tipoArticolo.getClass().getCanonicalName());
//		System.out.println(this.tipoArticolo.getClass().getSimpleName());
//		System.out.println(this.tipoArticolo.getClass().getTypeName());
		List<E> searchList = articoliService.selectByFilter(filter);
		
		if (searchList.size() == 0) {
			String ErrMsg = String.format("Non è stato trovato alcun oggetto con filtro %s", filter);
			logger.warn(ErrMsg);
			throw new NotFoundException(ErrMsg);
		} else {
			return new ResponseEntity<List<E>>(searchList, HttpStatus.OK);
		}

	}
	
	@GetMapping(value = "/aggiungi", produces = "application/json")
	public ResponseEntity<?> genericAddArticolo(BindingResult bindingResult) throws BindingException {
		
		logger.info("****** inserimento record ******");
		
		if (bindingResult.hasErrors()) {
			String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());
			
			logger.warn(MsgErr);

			throw new BindingException(MsgErr);
		}
			
//		checkForUpdate
		
		
		HttpHeaders headers = new HttpHeaders();
		ObjectMapper mapper = new ObjectMapper();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		ObjectNode responseNode = mapper.createObjectNode();
		
//		???
		articoliService.saveObject(null);
		
		responseNode.put("code", HttpStatus.OK.toString());
		responseNode.put("message", "Inserimento Articolo eseguita Con Successo");
		
		return new ResponseEntity<>(responseNode, headers, HttpStatus.CREATED);
	}
	
	
	@GetMapping(value = "/salva/{entity}", produces = "application/json")
	public void genericSaveEntity(@PathVariable("entity") E entity) {

		logger.info("****** salvataggio record ******");
		
		if (entity != null)
			articoliService.saveObject(entity);
	}
	
	@GetMapping(value = "/cancella/{entity}", produces = "application/json")
	public void genericDeleteEntity(@PathVariable("entity") E entity) {

		logger.info("****** eliminazione record ******");
		
		if (entity != null)
			articoliService.deleteObject(entity);
	}
	
	@GetMapping(value = "/cancella/{codArt}", produces = "application/json")
	public void genericDeleteEntityById(@PathVariable("codArt") String codArt) {

		logger.info("****** eliminazione record ******");
		
		E entity = articoliService.selectById(codArt);
		
		if (entity != null)
			articoliService.deleteObjectById(codArt);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	@Autowired
//	private BarcodeService barcodeService;
//	@Autowired
//	private ResourceBundleMessageSource errMessage;
//
//	------------------- Ricerca Per Barcode ------------------------------------
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
//	------------------- ELIMINAZIONE ARTICOLO ------------------------------------
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