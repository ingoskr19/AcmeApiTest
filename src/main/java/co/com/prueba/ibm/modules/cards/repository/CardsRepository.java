package co.com.prueba.ibm.modules.cards.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.prueba.ibm.common.base.BaseRepository;
import co.com.prueba.ibm.modules.cards.helper.CardsRowMapper;
import co.com.prueba.ibm.modules.cards.model.Card;

@Transactional
@Repository
public class CardsRepository extends BaseRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addCard(Card card) {
		String sql = "INSERT INTO cards (cardNumber,cardSerial,cvvCode,cardState,stateDate) values (?,?,?,?,?)";
		jdbcTemplate.update(sql, 
			card.getCardNumber(), 
			card.getCardSerial(), 
			card.getCvvCode(),
			card.getCardState(), 
			card.getExpiredDate());

		sql = "SELECT cardId FROM cards WHERE cardNumber = ? and cardSerial = ?";
		Card lastInsert = jdbcTemplate.queryForObject(sql, Card.class, card.getCardNumber(), card.getCardSerial());
		card.setCardId(lastInsert.getCardId());
	}
	
	public void updateCard(Card card) {
		String sql = "UPDATE cards SET cardNumber = ?,cardSerial = ?,cvvCode = ?, "
				+ "cardState = ?,stateDate = ?, expiredDate = ? where cardId = ?";
		jdbcTemplate.update(sql, 
				card.getCardNumber(), 
				card.getCardSerial(), 
				card.getCvvCode(),
				card.getCardState(), 
				card.getExpiredDate(),
				card.getCardId());
	}
	
	public void deleteCard(int cardId) {
		String sql = "DELETE FROM cards WHERE cardId=?";
		jdbcTemplate.update(sql, cardId);
	}
	
	public Card getCardById(int cardId) {
		String sql = "SELECT cardId,cardNumber,cardSerial,cvvCode,cardState,stateDate,expiredDate FROM cards WHERE cardId = ?";
		RowMapper<Card> rowMapper = new BeanPropertyRowMapper<Card>(Card.class);
		Card card = jdbcTemplate.queryForObject(sql, rowMapper, cardId);
		return card;
	}
	
	public List<Card> getAllCards() {
		String sql = "SELECT cardId,cardNumber,cardSerial,cvvCode,cardState,stateDate,expiredDate FROM cards";
		RowMapper<Card> rowMapper = new CardsRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}
}