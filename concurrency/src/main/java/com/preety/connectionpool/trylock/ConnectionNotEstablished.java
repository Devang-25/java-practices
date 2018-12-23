package com.preety.connectionpool.trylock;

public class ConnectionNotEstablished extends Exception {
	private String message;

	public ConnectionNotEstablished(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
