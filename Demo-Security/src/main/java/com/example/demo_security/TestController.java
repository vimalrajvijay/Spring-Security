package com.example.demo_security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@PostMapping("/register")
	public String openRegister() {
		return "Register Page";
	}
	
	@GetMapping("/home")
	public String openHome() {
		return "Home Page";
	}
	
	@GetMapping("/cart")
	public String openCart() {
		return "Cart Page";
	}
}
