package com.preety.service;

import org.springframework.stereotype.Component;

@Component
public class WelcomeService {

	public String getMessage() {
		return String.format("Welcome user");
	}
}
