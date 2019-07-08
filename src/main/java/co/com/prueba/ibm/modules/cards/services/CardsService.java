package co.com.prueba.ibm.modules.cards.services;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.prueba.ibm.common.base.BaseSpringService;
import co.com.prueba.ibm.modules.cards.model.Card;
import co.com.prueba.ibm.modules.cards.repository.CardsRepository;

@Component
public class CardsService extends BaseSpringService<CardsRepository> {
	
	public synchronized boolean addCard(Card card){
	    getRepository().addCard(card);
	    return true;
	}
	
	public void updateCard(Card card) {
		getRepository().updateCard(card);
	}
	
	public void deleteCard(int cardId) {
		getRepository().deleteCard(cardId);
	}
	
	public Card getCardById(int cardId) {
		Card obj = getRepository().getCardById(cardId);
		return obj;
	}	
	
	public List<Card> getAllCards(){
		return getRepository().getAllCards();
	}
	
	
} 