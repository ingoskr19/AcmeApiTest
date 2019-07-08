package co.com.prueba.ibm.modules.customers.services;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.prueba.ibm.common.base.BaseSpringService;
import co.com.prueba.ibm.modules.customers.model.Customer;
import co.com.prueba.ibm.modules.customers.repository.CustomerRepository;

@Component
public class CustomerService extends BaseSpringService<CustomerRepository> {
	
	/*@Autowired
	private CustomerRepository getRepository();*/
	
	public Customer getCustomerById(int customerId) {
		Customer obj = getRepository().getCustomerById(customerId);
		return obj;
	}	
	
	public List<Customer> getAllCustomers(){
		return getRepository().getAllCustomers();
	}
	
	public synchronized boolean createAccount(Customer customer){
	    getRepository().addCustomer(customer);
	    return true;
	}
	
	public void updateCustomer(Customer customer) {
		getRepository().updateCustomer(customer);
	}
	
	public void deleteCustomer(int customerId) {
		getRepository().deleteCustomer(customerId);
	}
} 