package com.tech.atm.util;

public enum ErrorReason {
	
	OUT_OF_CASH("ATM is out of cash. Cannot process transaction"),
	INVALID_AMOUNT("Invalid Amount -  Must be multiple of 5"),
	INSUFFICENT_FUNDS("Insuffient Funds");
	
	private final String description;

	private ErrorReason(String description) {
		this.description = description;
	}
	
	  @Override
	  public String toString() {
	    return description;
	  }
	
	

}
