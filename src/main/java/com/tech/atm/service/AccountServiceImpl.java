package com.tech.atm.service;

import static com.tech.atm.util.ErrorReason.INSUFFICENT_FUNDS;
import static com.tech.atm.util.ErrorReason.INVALID_AMOUNT;
import static com.tech.atm.util.ErrorReason.OUT_OF_CASH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.tech.atm.model.Account;
import com.tech.atm.model.Money;
import com.tech.atm.model.Response;
import com.tech.atm.repository.AccountRepository;
import com.tech.atm.repository.MoneyRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	Logger log = LogManager.getLogger(AccountServiceImpl.class);

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	MoneyRepository moneyRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		log.info("Initalising accounts and money");
		this.initAccounts();
		this.initMoney();
	}

	@Override
	public List<Account> getAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public List<Account> initAccounts() {
		List<Account> accounts = new ArrayList<>();
		accounts.add(new Account(123456789, 1234, 800, 200));
		accounts.add(new Account(987654321, 4321, 1230, 150));

		return accountRepository.saveAll(accounts);
	}

	public List<Money> initMoney() {
		List<Money> monies = new ArrayList<>();

		monies.add(new Money(50, 10));
		monies.add(new Money(20, 30));
		monies.add(new Money(10, 30));
		monies.add(new Money(5, 20));

		return moneyRepository.saveAll(monies);
	}
	
    /**
	 * Withdraw amount from account.
     *
     * @param accountNo, amount
     * @return response This response contains updated
     * 					account details and amount and
     * 					type of bank notes received
     */
	@Override
	public Response withdraw(int accountNo, int amount) {
		Account account = this.getAccount(accountNo);

		if (amount > this.cashLeft()) {
			log.info(OUT_OF_CASH.toString());
			throw new OutOfCashException(OUT_OF_CASH.toString());
		}

		if (amount > (account.getBalance() + account.getOverDraft())) {
			log.info(INSUFFICENT_FUNDS.toString());
			throw new InsufficientFundsException(INSUFFICENT_FUNDS.toString());
		}

		if (amount % 5 != 0) {
			log.info(INVALID_AMOUNT.toString());
			throw new InsufficientFundsException(INVALID_AMOUNT.toString());
		}

		String notes = Arrays.asList(this.computeNotes(amount)).toString();
		account.setBalance(account.getBalance() - amount);
		accountRepository.save(account);

		return new Response(account, notes);
	}

	private int cashLeft() {
		List<Money> availableMonies = moneyRepository.findAll();
		int amount = 0;
		for (Money money : availableMonies) {
			amount += money.getType() * money.getAmount();
		}

		return amount;
	}
	
    /**
	 * Compute notes to be dispense based on withdrawal amount.
     *
     * @param accountNo, amount
     * @return List<Money> Contains bank note type and amount
     * 						per index
     */

	private List<Money> computeNotes(int amount) {
		List<Money> availableMonies = moneyRepository.findAll();
		List<Money> requestedMoney = new ArrayList<>();

		int withdrawalAmount = amount;
		int dispense = 0;
		// increase dispense with available bank notes until desired amount is reached
		while (dispense < amount) {
			for (Money money : availableMonies) {
				int count = 0;
				while (money.getType() <= amount) {
					if (money.getAmount() <= 0) {
						break;
					}
					dispense += money.getType();
					amount = amount - money.getType();
					count++;
					money.setAmount(money.getAmount() - 1);
				}
				requestedMoney.add(new Money(money.getType(), count));
			}
		}
		
		moneyRepository.saveAll(availableMonies);
		
		return requestedMoney;
	}

	@Override
	public Account getAccount(int accountNo) {
		return accountRepository.findById(accountNo).orElseThrow(() -> new EntityNotFoundException());
	}
}
