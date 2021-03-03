package com.tech.atm.service;

public class OutOfCashException extends RuntimeException {

	public OutOfCashException(String outOfCashException) {
		super(outOfCashException);
	}

}
