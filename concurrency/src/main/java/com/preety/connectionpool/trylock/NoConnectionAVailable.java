package com.preety.connectionpool.trylock;

public class NoConnectionAVailable extends Exception {
	private String message;
	public NoConnectionAVailable(String message) {
		this.message= message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
