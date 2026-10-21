package com.example.demo_security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_security.dto.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	
	public AuthController(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}


	@PostMapping
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
		return new ResponseEntity<String>("Login successful", HttpStatus.OK);
	}
	
}
