package com.tech.atm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tech.atm.model.Account;
import com.tech.atm.model.Money;
import com.tech.atm.model.Response;
import com.tech.atm.service.AccountServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class AtmApplicationTests {
	
	@Autowired
	AccountServiceImpl accountServiceImpl;
	
	@Test
	public void checkIfAccountsInit() {
		List<Account> accounts = accountServiceImpl.initAccounts();
		assertEquals(2, accounts.size());
	}
	
	@Test
	public void checkIfMoniesInit() {
		List<Money> monies = accountServiceImpl.initMoney();
		assertEquals(4, monies.size());
	}

	@Test
	public void whenUserWithdrawsFunds_checkIfBalanceUpdated() {
		Response response = accountServiceImpl.withdraw(123456789, 100);
		assertEquals(response.getAccount().getBalance(), 700);
	}

	@Test
	void contextLoads() {
	}

}
