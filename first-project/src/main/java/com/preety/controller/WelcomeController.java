package com.preety.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.preety.service.WelcomeService;

@Controller
public class WelcomeController {
	
	@Autowired
	private WelcomeService service;
	
	@RequestMapping("/welcome")
	@ResponseBody
	public String getWelcomeMessage() {
		return service.getMessage();
	}

}
