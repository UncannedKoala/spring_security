package com.spring_security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_security.entity.Object;

@RestController
public class BasicController {

	@GetMapping("/greet")
	public String greet() {
		return "Hello";
	}

	/*
	 * -----------------------------------------------------------------------------
	 *                BELOW CODE IS NOT RELATED TO SPRING SECURITY
	 * -----------------------------------------------------------------------------
	 */

//	API to demonstrate accepting value from a path variable
	@GetMapping("/hello/{val}")
	String hello(@PathVariable String val) {
		return "hello " + val;
	}

//	API to demonstrate accepting value from a request parameter
	@GetMapping("/yolo")
	String hello(@RequestParam int val) {
		return "yolo " + val;
	}

//	API to show case object parsing capabilities of spring boot && THAT WE CAN HAVE AN OBJECT NAMED 'Object'
	@GetMapping("/goGetIt")
	public Object goGetIt() {
		Object obj = new com.spring_security.entity.Object("sample", 10, 20);
		return obj;
	}

//	API to show case the object parsing capabilities of spring boot
	@GetMapping("/HereTakeIt")
	public Object hereTakeIt(@RequestBody Object obj) {
		return obj;
	}
}
