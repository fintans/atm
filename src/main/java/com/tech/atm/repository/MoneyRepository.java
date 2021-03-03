package com.tech.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.atm.model.Money;

public interface MoneyRepository extends JpaRepository<Money, Long> {

}
