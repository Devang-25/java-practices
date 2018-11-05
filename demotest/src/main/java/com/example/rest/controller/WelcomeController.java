package com.example.rest.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.beans.StudentRegisteration;
import com.example.beans.StudentRegistrationReply;
import com.example.beans.StudentVerification;
import com.example.beans.Welcome;

@Controller
public class WelcomeController {
	private static final String welcommsg= "Welcome %s";
	
	@GetMapping("/welcome/user")
	@ResponseBody
	public Welcome welcomeUser(@RequestParam(name= "name", required= false, defaultValue= "Java fan") String name) {
		return new Welcome(String.format(welcommsg, name));
	}
	
	@RequestMapping("/student")
	@ResponseBody
	public StudentRegistrationReply getStudent(@RequestParam String name) {
		
		
		return stdregrreply;
	}
	
	@RequestMapping(method= RequestMethod.POST, value= "/register/student")
	@ResponseBody
	public StudentRegistrationReply registerStudent(@RequestBody StudentRegisteration stdregr) {
		StudentRegistrationReply stdregrreply= new StudentRegistrationReply();
		stdregrreply.setName(stdregr.getName());
		stdregrreply.setAge(stdregr.getAge());
		stdregrreply.setRegistrationNumber("1234");
		stdregrreply.setRegistrationStatus("success");
		stdregrreply.setVerified(false);
		
		return stdregrreply;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/verify/student")
	@ResponseBody
	public StudentRegistrationReply verifyStudent(@RequestParam(name="name", required=true)String name, @RequestBody StudentVerification stdregr) {
		StudentRegistrationReply stdregrreply= new StudentRegistrationReply();
		stdregrreply.setName(stdregr.getName());
		stdregrreply.setVerified(true);
		
		return stdregrreply; 
	}

}
