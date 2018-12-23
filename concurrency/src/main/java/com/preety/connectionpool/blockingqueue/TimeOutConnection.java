package com.preety.connectionpool.blockingqueue;

public class TimeOutConnection extends Exception {

	private String message;

	public TimeOutConnection(String message) {
		this.message=message;
	}

}
