package co.com.prueba.ibm.modules.cards.model;

import java.util.Date;

import co.com.prueba.ibm.common.base.BaseModel;

public class Card extends BaseModel {

	private long cardId;
	private String cardNumber;
	private String cardSerial;
	private String cvvCode;
	private int cardState;
	private Date stateDate;
	private Date expiredDate;

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardSerial() {
		return cardSerial;
	}

	public void setCardSerial(String cardSerial) {
		this.cardSerial = cardSerial;
	}

	public String getCvvCode() {
		return cvvCode;
	}

	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}

	public int getCardState() {
		return cardState;
	}

	public void setCardState(int cardState) {
		this.cardState = cardState;
	}

	public Date getStateDate() {
		return stateDate;
	}

	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	
}
