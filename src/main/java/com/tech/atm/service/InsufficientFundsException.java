package com.tech.atm.service;

import com.tech.atm.util.ErrorReason;

public class InsufficientFundsException extends RuntimeException  {

	public InsufficientFundsException(String insufficentFunds) {
		super(insufficentFunds);
	}

}
