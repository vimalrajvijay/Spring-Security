package com.example.demo_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@PostMapping("/register")
	public String openRegister() {
		return "Register Page";
	}
	
	@PostMapping("/admin")
	public String openHome() {
		return "Admin Page";
	}
	
	@GetMapping("/user")
	public String openCart() {
		return "User Page";
	}
}
