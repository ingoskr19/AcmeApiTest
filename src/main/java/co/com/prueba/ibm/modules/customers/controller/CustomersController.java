package co.com.prueba.ibm.modules.customers.controller;

import java.util.List;

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

import co.com.prueba.ibm.common.base.BaseResponse;
import co.com.prueba.ibm.common.base.BaseSpringController;
import co.com.prueba.ibm.modules.customers.model.Customer;
import co.com.prueba.ibm.modules.customers.services.CustomerService;

@RestController
@RequestMapping(path = "/customers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomersController extends BaseSpringController<CustomerService> {

	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	public ResponseEntity<Void> addCustomer(@RequestBody Customer customer, UriComponentsBuilder builder) {
		boolean flag = getService().createAccount(customer);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/customer/{id}").buildAndExpand(customer.getCustomerId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		getService().updateCustomer(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.POST)
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Integer id) {
		getService().deleteCustomer(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = { "/get/{id}" }, method = RequestMethod.POST)
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id) {
		Customer customer = getService().getCustomerById(id);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public ResponseEntity<BaseResponse<List<Customer>>> getAllCustomers() {
		List<Customer> list = getService().getAllCustomers();
		BaseResponse<List<Customer>> response = new BaseResponse<List<Customer>>();
		response.setModel(list);
		return new ResponseEntity<BaseResponse<List<Customer>>>(response, HttpStatus.OK);
	}
}