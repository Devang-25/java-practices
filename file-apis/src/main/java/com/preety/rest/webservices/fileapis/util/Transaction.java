package com.preety.rest.webservices.fileapis.util;

public class Transaction {

	public static final String GROCERY = "grocery";
	private String type;
	private long value;
	private int id;
	
	

	public Transaction(String type, long value, int id) {
		super();
		this.type = type;
		this.value = value;
		this.id = id;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	
	public long getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

}
