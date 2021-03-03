package com.tech.atm.service;

import java.util.List;

import com.tech.atm.model.Account;
import com.tech.atm.model.Response;

public interface AccountService {
	
	List<Account> initAccounts();
	
	Response withdraw(int accountNo, int amount);
	
	Account getAccount(int accountNo);

	List<Account> getAccounts();

}
