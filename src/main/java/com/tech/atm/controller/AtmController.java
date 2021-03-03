package com.tech.atm.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.atm.model.Account;
import com.tech.atm.model.Response;
import com.tech.atm.service.AccountService;

@RestController
@RequestMapping("/api/v1")
public class AtmController {
	
	Logger log = LogManager.getLogger(AtmController.class);

	@Autowired
	private AccountService accountService;
	
	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> getAccounts() {
		return ResponseEntity.ok().body(accountService.getAccounts());
	}
	
	@PutMapping("/accounts/withdraw/{amount}")
	public ResponseEntity<Response> withdraw(Authentication authentication, @PathVariable(value = "amount") int amount) {
		log.info(authentication.getName());
		return ResponseEntity.ok().body(accountService.withdraw(Integer.parseInt(authentication.getName()), amount));
	}
}