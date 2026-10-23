package com.example.demo_security.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private final SecretKey SECRET_KEY; // Representing Secret Key
	private final long EXP_TIME = 60*60*24; // Valid for 1 day
	
	public JwtService(@Value("${jwt.secret}") String key) {
		SECRET_KEY= Keys.hmacShaKeyFor(key.getBytes()); // Creating key using HMAC-SHA
	}
	
	public String generateToken(String username) {
		return Jwts.builder()// Start building token
		.setSubject(username) // Adding username as Subject 
		.setIssuedAt(new Date(System.currentTimeMillis()))// setting current time in Milliseconds
		.setExpiration(new Date(System.currentTimeMillis()// setting Exp time in Milliseconds
				+EXP_TIME))
		.signWith(SECRET_KEY)// Adding SECRET_KEY for creating signature  
		.compact(); // Returns final token
	}
	
	
	public String getUserName(String token) {
		return Jwts.parserBuilder()// Building JwtParser
		.setSigningKey(SECRET_KEY) // Setting SECRET_KEY for Validating token 
		.build()                   // Creates JwtParser
		.parseClaimsJws(token)     // Parsing token and validating token
		.getBody()                 // Returns JWT payload
		.getSubject();             // Returns Username
	}
	
	// Returns true if the Token is Valid
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()       // Building JwtParser
			.setSigningKey(SECRET_KEY) // Setting SECRET_KEY for Validating token
			.build()                   // Creates JwtParser
			.parseClaimsJws(token);    // Parsing token and validating token
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
