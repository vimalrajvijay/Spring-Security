package com.example.demo_security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo_security.service.CustomUserDetailsService;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(
			HttpSecurity http) {
		
		http.csrf(csrf->csrf.disable());
		http.authorizeHttpRequests((auth)->
		       auth.requestMatchers("/register","/api/users", "/api/auth").permitAll()
		       .anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults());
		//.formLogin(form->form.permitAll());
		
		return http.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider(
			PasswordEncoder passwordEncoder, 
			CustomUserDetailsService userDetailsService) {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider(userDetailsService);
		dao.setPasswordEncoder(passwordEncoder);
		return dao;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
