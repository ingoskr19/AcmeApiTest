package co.com.prueba.ibm.modules.cards.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import co.com.prueba.ibm.common.base.GenericRowMapper;
import co.com.prueba.ibm.modules.cards.model.Card;
import co.com.prueba.ibm.modules.customers.model.Customer;

public class CardsRowMapper extends GenericRowMapper implements RowMapper<Card> {
	
	RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<Customer>(Customer.class);
	
	public Card mapRow(ResultSet row, int rowNum) throws SQLException {
		return mapFromRS(row);
	}
	
	public Card mapFromRS(ResultSet row) throws SQLException {
		Card card = new Card();
		
		if(existsColumn("cardId",Types.INTEGER, row))
			card.setCardId(row.getInt("cardId"));
		
		if(existsColumn("cardNumber",Types.VARCHAR, row))
			card.setCardNumber(row.getString("cardNumber"));
		
		if(existsColumn("cardSerial",Types.VARCHAR, row))
			card.setCardSerial(row.getString("cardSerial"));
		
		if(existsColumn("cvvCode",Types.VARCHAR, row))
			card.setCvvCode(row.getString("cvvCode"));
		
		if(existsColumn("cardState",Types.INTEGER, row))
			card.setCardState(row.getInt("cardState"));
		
		if(existsColumn("stateDate",Types.DATE, row))
			card.setStateDate(row.getDate("stateDate"));
		
		if(existsColumn("expiredDate",Types.DATE, row))
			card.setExpiredDate(row.getDate("expiredDate"));
		
		return card;
	}
	
	
	
} 