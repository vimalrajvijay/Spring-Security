package com.example.demo_security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> handleAuthendicationException(AuthenticationException ex){
		String message = ex.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.UNAUTHORIZED);
	}
}
