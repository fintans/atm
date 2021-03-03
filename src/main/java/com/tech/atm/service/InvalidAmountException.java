package com.tech.atm.service;

public class InvalidAmountException extends RuntimeException {
	
	public InvalidAmountException(String invalidAmount) {
		super(invalidAmount);
	}

}
