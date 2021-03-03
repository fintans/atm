package com.tech.atm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tech.atm.service.InsufficientFundsException;
import com.tech.atm.service.InvalidAmountException;
import com.tech.atm.service.OutOfCashException;

@ControllerAdvice
public class ExceptionHelper {
	
	@ExceptionHandler(value = {InsufficientFundsException.class})
	protected ResponseEntity<Object> handleInsufficientFundsException(InsufficientFundsException ife) {
		return new ResponseEntity<Object>(ife.getMessage(),HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = {InvalidAmountException.class})
	protected ResponseEntity<Object> handleInvalidAmountException(InvalidAmountException iae) {
		return new ResponseEntity<Object>(iae.getMessage(),HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = {OutOfCashException.class})
	protected ResponseEntity<Object> handleOutOfCashException(OutOfCashException ooce) {
		return new ResponseEntity<Object>(ooce.getMessage(),HttpStatus.FORBIDDEN);
	}

}
