package co.com.prueba.ibm.modules.accounts.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

import co.com.prueba.ibm.common.base.BaseSpringController;
import co.com.prueba.ibm.modules.accounts.model.Account;
import co.com.prueba.ibm.modules.accounts.services.AccountsService;

@RestController
@RequestMapping(path = "/accounts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountsController extends BaseSpringController<AccountsService> {

	@RequestMapping(value = { "/create" }, method = RequestMethod.POST)
	public ResponseEntity<Void> createAccount(@RequestBody Account account, UriComponentsBuilder builder) {
		boolean flag = getService().createAccount(account);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/account/{id}").buildAndExpand(account.getAccountId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = { "/assignCard" }, method = RequestMethod.POST)
	public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
		getService().updateAccount(account);
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	@RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.POST)
	public ResponseEntity<Void> deleteAccount(@PathVariable("id") Integer id) {
		getService().deleteAccount(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = { "/get/{id}" }, method = RequestMethod.POST)
	public ResponseEntity<Account> getAccountById(@PathVariable("id") Integer id) {
		Account account = getService().getAccountById(id);
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.POST)
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> list = getService().getAllAccounts();
		return new ResponseEntity<List<Account>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/assignedTo/{customerId}" }, method = RequestMethod.POST)
	public ResponseEntity<List> getAccountByCustomer(@PathVariable("customerId") Integer id) {
		List<Map<String,Object>> list = getService().getAccountByCustomer(id);
		return new ResponseEntity<List>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/filterByDate" }, method = RequestMethod.POST)
	public ResponseEntity<List> filterByDate(@RequestBody Map<String,Date> request ) {
		List<Map<String,Object>> list = getService().filterByDate(request.get("from"),request.get("to"));
		return new ResponseEntity<List>(list, HttpStatus.OK);
	}
}