package co.com.prueba.ibm.modules.accounts.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import co.com.prueba.ibm.common.base.BaseSpringService;
import co.com.prueba.ibm.modules.accounts.model.Account;
import co.com.prueba.ibm.modules.accounts.repository.AccountsRepository;

@Component
public class AccountsService extends BaseSpringService<AccountsRepository> {
	
	public Account getAccountById(int accountId) {
		Account obj = getRepository().getAccountById(accountId);
		return obj;
	}	
	
	public List<Account> getAllAccounts(){
		return getRepository().getAllAccounts();
	}
	
	public synchronized boolean createAccount(Account account){
	    getRepository().createAccount(account);
	    return true;
	}
	
	public void updateAccount(Account account) {
		getRepository().updateAccount(account);
	}
	
	public void deleteAccount(int accountId) {
		getRepository().deleteAccount(accountId);
	}
	
	public List<Map<String,Object>> getAccountByCustomer(int accountId) {
		return getRepository().getAccountByCustomer(accountId);
	}

	public List<Map<String, Object>> filterByDate(Date from, Date to) {
		return getRepository().filterByDate(from,to);
	}
} 