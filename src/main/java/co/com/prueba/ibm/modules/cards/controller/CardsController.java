package co.com.prueba.ibm.modules.cards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import co.com.prueba.ibm.common.base.BaseSpringController;
import co.com.prueba.ibm.modules.cards.model.Card;
import co.com.prueba.ibm.modules.cards.services.CardsService;

@RestController
@RequestMapping(path = "/cards")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CardsController extends BaseSpringController<CardsService> {
	
	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	public ResponseEntity<Void> addCard(@RequestBody Card customer, UriComponentsBuilder builder) {
		boolean flag = getService().addCard(customer);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/customer/{id}").buildAndExpand(customer.getCardId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public ResponseEntity<Card> updateCard(@RequestBody Card customer) {
		getService().updateCard(customer);
		return new ResponseEntity<Card>(customer, HttpStatus.OK);
	}

	@RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.POST)
	public ResponseEntity<Void> deleteCard(@PathVariable("id") Integer id) {
		getService().deleteCard(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = { "/get/{id}" }, method = RequestMethod.POST)
	public ResponseEntity<Card> getCardById(@PathVariable("id") Integer id) {
		Card customer = getService().getCardById(id);
		return new ResponseEntity<Card>(customer, HttpStatus.OK);
	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public ResponseEntity<List<Card>> getAllCards() {
		List<Card> list = getService().getAllCards();
		return new ResponseEntity<List<Card>>(list, HttpStatus.OK);
	}
	
}