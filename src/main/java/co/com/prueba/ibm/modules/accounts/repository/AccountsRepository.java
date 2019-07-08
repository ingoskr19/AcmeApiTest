package co.com.prueba.ibm.modules.accounts.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.prueba.ibm.common.base.BaseRepository;
import co.com.prueba.ibm.modules.accounts.helper.AccountRowMapper;
import co.com.prueba.ibm.modules.accounts.model.Account;

@Transactional
@Repository
public class AccountsRepository extends BaseRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void createAccount(Account account) {
		String sql = "INSERT INTO accounts (customerId,productId,cardId,creationDate,accountState) values (?,?,?,?,?)";
		jdbcTemplate.update(sql, 
			account.getCustomerId(), 
			account.getProductId(), 
			account.getCardId(),
			account.getCreationDate(), 
			account.getAccountState()
			);

		sql = "SELECT accountId FROM accounts WHERE customerId = ? and productId = ?";
		Account lastInsert = jdbcTemplate.queryForObject(sql, Account.class, account.getCustomerId(), account.getProductId());
		account.setAccountId(lastInsert.getAccountId());
	}
	
	public void updateAccount(Account account) {
		String sql = "UPDATE accounts SET customerId = ?, productId = ?, cardId = ?,"
				+ "creationDate = ?,accountState = ? where accountId = ?";
		jdbcTemplate.update(sql, 
				account.getCustomerId(), 
				account.getProductId(), 
				account.getCardId(),
				account.getCreationDate(), 
				account.getAccountState(),
				account.getAccountId());
	}
	
	public void assignCard(int accountId, int cardId) {
		String sql = "UPDATE accounts SET cardId = ? where accountId = ?";
		jdbcTemplate.update(sql, cardId, accountId);
	}
	
	public void deleteAccount(int accountId) {
		String sql = "DELETE FROM accounts WHERE accountId=?";
		jdbcTemplate.update(sql, accountId);
	}
	
	public Account getAccountById(int accountId) {
		String sql = "SELECT accountId,customerId,productId,cardId,creationDate,accountState FROM accounts WHERE accountId = ?";
		RowMapper<Account> rowMapper = new BeanPropertyRowMapper<Account>(Account.class);
		Account account = jdbcTemplate.queryForObject(sql, rowMapper, accountId);
		return account;
	}
	
	public List<Account> getAllAccounts() {
		String sql = "SELECT accountId,customerId,productId,cardId,creationDate,accountState FROM accounts";
		RowMapper<Account> rowMapper = new AccountRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	public List<Map<String,Object>> getAccountByCustomer(int customer) {
		String sql = "SELECT " + 
				"ac.accountId, ac.creationDate, c.customerId, c.names, c.lastnames, " + 
				"c.documentNumber, p.desProduct, p.productId, ca.cardId, ca.cardNumber " + 
				"FROM accounts ac " + 
				"INNER JOIN cards ca ON ca.cardId = ac.cardId " + 
				"INNER JOIN products p ON p.productId = ac.productId " + 
				"INNER JOIN customers c ON c.customerId = ac.customerId WHERE ac.customerId = ?";
		
		return jdbcTemplate.queryForList(sql, customer);
	}

	public List<Map<String, Object>> filterByDate(Date from, Date to) {
		String sql = "SELECT * FROM SaleReport WHERE creationDate between TIMESTAM(?) AND TIMESTAMP(?)";
		return jdbcTemplate.queryForList(sql, from.getTime(), to.getTime());
	}
}