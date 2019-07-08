package co.com.prueba.ibm.modules.customers.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import co.com.prueba.ibm.common.base.GenericRowMapper;
import co.com.prueba.ibm.modules.customers.model.Customer;

public class CustomerRowMapper extends GenericRowMapper implements RowMapper<Customer> {
	
	RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<Customer>(Customer.class);
	
	public Customer mapRow(ResultSet row, int rowNum) throws SQLException {
		return mapFromRS(row);
	}
	
	public Customer mapFromRS(ResultSet row) throws SQLException {
		Customer customer = new Customer();
		customer.setCustomerId(row.getInt("customerId"));
		customer.setNames(row.getString("names"));
		customer.setLastNames(row.getString("lastNames"));
		customer.setDocumentType(row.getString("documentType"));
		customer.setDocumentNumber(row.getString("documentNumber"));
		customer.setBirthDate(row.getDate("birthDate"));
		return customer;
	}
	
} 