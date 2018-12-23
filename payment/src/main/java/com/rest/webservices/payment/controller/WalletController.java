package com.rest.webservices.payment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservices.payment.domain.Wallet;

@RestController
public class WalletController {

	@GetMapping("user/{userId}")
	public Wallet getWalletByUserId(@PathVariable int userId) {
		return null;
	}
	
	@PostMapping("/user/{userId}")
	public void createWallet() {
		
	}
}
