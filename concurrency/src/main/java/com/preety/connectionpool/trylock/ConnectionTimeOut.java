package com.preety.connectionpool.trylock;

public class ConnectionTimeOut extends Exception {
	private String message;
	
	public ConnectionTimeOut(String message) {
		this.message= message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
