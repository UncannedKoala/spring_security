package com.spring_security.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

	@GetMapping("/greet")
	public String greet() {
		var u = SecurityContextHolder.getContext().getAuthentication();
		u.getAuthorities().forEach(System.out::println);
		return "Hello";
	}
}
