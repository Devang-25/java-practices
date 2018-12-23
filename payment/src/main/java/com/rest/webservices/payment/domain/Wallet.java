package com.rest.webservices.payment.domain;

import java.util.List;

public class Wallet {
	
	private int id;
	private int userId;
	private double amount;


	public Wallet(int userId, double amount) {
		super();
		this.userId = userId;
		this.amount= amount;
	}
	public int getId() {
		return id;
	}
	public int getUserId() {
		return userId;
	}
	public double getAmount() {
		return amount;
	}
	
	
}
