package com.example.beans;

public class Welcome {
	private String  message;
	public Welcome(String message) {
		super();
		this.message=message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
}
//Spring uses the Jackson JSON library to automatically marshal instances of type Welcome into JSON.
