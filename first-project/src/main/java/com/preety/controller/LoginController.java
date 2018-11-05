package com.preety.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showLoginPage(@RequestParam String name, ModelMap model) {
		model.put("name", name);
		return "welcome";
	}

}
