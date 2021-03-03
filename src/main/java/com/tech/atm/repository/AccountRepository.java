package com.tech.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.atm.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

}
