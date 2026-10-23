package com.example.demo_security.service;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final CustomUserDetailsService customUserDetailsService;

	public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
		super();
		this.jwtService = jwtService;
		this.customUserDetailsService = customUserDetailsService;
	}

	// Executes for every request
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Reading Authorization Header
		String header = request.getHeader("Authorization");
		System.out.println(header);
		// Checking Header
		if (header != null && header.startsWith("Bearer ")) {
			// Extracting Token
			String token = header.substring(7);
			// Validating Token
			if (jwtService.validateToken(token)) {
				// Extracting username
				String username = jwtService.getUserName(token);

				// Creating userDetails using customUserDetailsService
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
				
				//Creating Authentication Object
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				
				// Storing Authentication object in Security context
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		// Passing the request and response objects to the next Filter 
		filterChain.doFilter(request, response);
	}
}
