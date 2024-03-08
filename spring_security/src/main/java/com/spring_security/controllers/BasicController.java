package com.spring_security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

	@GetMapping("/greet")
	public String greet() {
		return "Hello";
	}
}
