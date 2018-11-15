package com.preety.rest.webservices.springbootwebservice;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringbootWebServiceApplication {

	public static void main(String[] args) {
		System.out.println("today is " + new Date());
		
		SpringApplication.run(SpringbootWebServiceApplication.class, args);
	}
}
