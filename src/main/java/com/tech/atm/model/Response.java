package com.tech.atm.model;

public class Response {
	
	private Account account;
	private String notesRecieved;
	
	public Response(Account account, String notesRecieved) {
		super();
		this.account = account;
		this.notesRecieved = notesRecieved;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getNotesRecieved() {
		return notesRecieved;
	}

	public void setNotesRecieved(String notesRecieved) {
		this.notesRecieved = notesRecieved;
	}
}
