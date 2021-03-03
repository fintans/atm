package com.tech.atm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	@Id
	private int accountNo;
	private int pin;
	private int balance;
	private int overDraft;
	
	
	public Account() {
		super();
	}

	public Account(int accountNo, int pin, int balance, int overDraft) {
		super();
		this.accountNo = accountNo;
		this.pin = pin;
		this.balance = balance;
		this.overDraft = overDraft;
	}
	
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getOverDraft() {
		return overDraft;
	}
	public void setOverDraft(int overDraft) {
		this.overDraft = overDraft;
	}
	
	
	

}
