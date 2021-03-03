package com.tech.atm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Money {
	
	private int type;
	private int amount;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	public Money() {
		super();
	}
	public Money(int type, int amount) {
		super();
		this.type = type;
		this.amount = amount;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "€" + this.type + " x " + this.amount;
	}
	
	

}
