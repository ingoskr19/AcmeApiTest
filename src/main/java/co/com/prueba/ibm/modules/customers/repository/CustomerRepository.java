package co.com.prueba.ibm.modules.customers.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.prueba.ibm.common.base.BaseRepository;
import co.com.prueba.ibm.modules.customers.helper.CustomerRowMapper;
import co.com.prueba.ibm.modules.customers.model.Customer;

@Transactional
@Repository
public class CustomerRepository extends BaseRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addCustomer(Customer customer) {
		String sql = "INSERT INTO customers (names,lastnames,documentType,documentNumber,birthDate) values (?,?,?,?,?)";
		jdbcTemplate.update(sql, 
			customer.getNames(), 
			customer.getLastNames(), 
			customer.getDocumentType(),
			customer.getDocumentNumber(), 
			customer.getBirthDate());
	}
	
	public void updateCustomer(Customer customer) {
		String sql = "UPDATE customers SET names = ?,lastnames = ?,documentType = ?,documentNumber = ?,birthDate = ? where customerId = ?";
		jdbcTemplate.update(sql, 
				customer.getNames(), 
				customer.getLastNames(), 
				customer.getDocumentType(),
				customer.getDocumentNumber(), 
				customer.getBirthDate(),
				customer.getCustomerId());
	}
	
	public void deleteCustomer(int customerId) {
		String sql = "DELETE FROM customers WHERE customerId=?";
		jdbcTemplate.update(sql, customerId);
	}
	
	public Customer getCustomerById(int customerId) {
		String sql = "SELECT customerId,names,lastnames,documentType,documentNumber,birthDate FROM customers WHERE customerId = ?";
		RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<Customer>(Customer.class);
		Customer customer = jdbcTemplate.queryForObject(sql, rowMapper, customerId);
		return customer;
	}
	
	public List<Customer> getAllCustomers() {
		String sql = "SELECT customerId,names,lastnames,documentType,documentNumber,birthDate FROM customers";
		RowMapper<Customer> rowMapper = new CustomerRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}
}