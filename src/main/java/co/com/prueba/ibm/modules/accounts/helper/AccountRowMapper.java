package co.com.prueba.ibm.modules.accounts.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import co.com.prueba.ibm.common.base.GenericRowMapper;
import co.com.prueba.ibm.modules.accounts.model.Account;

public class AccountRowMapper extends GenericRowMapper implements RowMapper<Account> {

	RowMapper<Account> rowMapper = new BeanPropertyRowMapper<Account>(Account.class);

	public Account mapRow(ResultSet row, int rowNum) throws SQLException {
		return mapFromRS(row);
	}

	// customerId,productId,cardId,creationDate,accountState
	public Account mapFromRS(ResultSet row) throws SQLException {
		Account account = new Account();

		if (existsColumn("accountId", Types.INTEGER, row))
			account.setAccountId(row.getInt("accountId"));

		if (existsColumn("customerId", Types.INTEGER, row))
			account.setCustomerId(row.getInt("customerId"));

		if (existsColumn("productId", Types.INTEGER, row))
			account.setProductId(row.getInt("productId"));

		if (existsColumn("cardId", Types.INTEGER, row))
			account.setCardId(row.getInt("cardId"));

		if (existsColumn("cardId", Types.DATE, row))
			account.setCreationDate(row.getDate("creationDate"));

		if (existsColumn("accountState", Types.INTEGER, row))
			account.setAccountState(row.getInt("accountState"));

		return account;
	}

}