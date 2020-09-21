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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@GetMapping(value = "/cerca/codice/{id}", produces = "application/json")
	public ResponseEntity<G> genericSearchById(@PathVariable("id") String id, HttpServletRequest httpRequest) throws NotFoundException {

		logger.info("****** Ricerca filtrata per codice: " + id + "!");
		G dtoObject = null;
		
		if(id.matches("[0-9]+")) {
			Integer codConverted = Integer.valueOf(id);
			E articolo = articoliService.selectById(codConverted);
			
			if (articolo == null) {
				String errMsg = String.format("Non è stato trovato alcun articolo con codice: ", id);
				logger.warn(errMsg);
				
				throw new NotFoundException(errMsg);
			} else {
				String authHeader = httpRequest.getHeader("Authorization");
				dtoObject = (G) articolo.convertArticoliToDTO();
				
				dtoObject.setPrezzo(this.getPriceArt(id, "", authHeader));
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
		List <E> searchList;
		if(description.equalsIgnoreCase("undefined")) {
			System.out.println("findall");
			searchList = articoliService.findAll();
		} else {
			searchList = articoliService.selectByDescription(description);
		}
		

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

			searchList.forEach(f -> f.setPrezzo(this.getPriceArt(f.getId().toString(), "", authHeader)));
		}

		return new ResponseEntity<List<G>>(listDto, HttpStatus.OK);
	}

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
	
	@PutMapping(value = "/inserisci", produces = "application/json")
	public ResponseEntity<?> genericUpdateObject(@Valid @RequestBody E object, HttpServletRequest httpRequest, BindingResult bindingResult) throws BindingException, DuplicateException {

		logger.info("****** aggiornamento record ******");

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
		responseNode.put("message", "Aggiornamento Articolo eseguito Con Successo");

		return new ResponseEntity<>(responseNode, headers, HttpStatus.CREATED);
	}


	@DeleteMapping(value = "/elimina/codice/{id}", produces = "application/json")
	public ResponseEntity<?> genericDeleteById(@PathVariable("id") Integer id) throws NotFoundException {
		
		logger.info("Eliminiamo l'articolo con codice " + id);

		HttpHeaders headers = new HttpHeaders();
		ObjectMapper mapper = new ObjectMapper();

		headers.setContentType(MediaType.APPLICATION_JSON);
		ObjectNode responseNode = mapper.createObjectNode();
		
		E object = articoliService.selectById(id);
		
		if (object != null) {
			articoliService.deleteObjectById(id);
			responseNode.put("message", "Eliminazione Articolo " + id + " Eseguita Con Successo");
		} else {
			responseNode.put("message", "Eliminazione Articolo " + id + " non eseguita!");
		}
		
		responseNode.put("code", HttpStatus.OK.toString());
		
		return new ResponseEntity<>(responseNode, headers, HttpStatus.OK);
	}

	private Double getPriceArt(String Id, String IdList, String Header) {
		try {
			Double Prezzo = (IdList.length() > 0) ? priceClient.getPriceArt(Header, Id, IdList)
					: priceClient.getDefPriceArt(Header, Id);
			logger.info("Prezzo Articolo " + Id + ": " + Prezzo);

			return Prezzo;
		} catch (Exception ex) {
			logger.error("Errore ottenimento prezzo: " + ex.getMessage());
			return 0.0;
		}
	}

	
	

}
