package com.example.demo_security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(
			HttpSecurity http) {
		
		http.authorizeHttpRequests(auth->
		       auth.requestMatchers("/register").permitAll()
		       .anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults());
		//.formLogin(form->form.permitAll());
		
		return http.build();
	}
}
