package com.example.demo_security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo_security.service.CustomUserDetailsService;
import com.example.demo_security.service.JwtAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(
			HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter ) {
		
		//Disables csrf protection
		http.csrf(csrf->csrf.disable());
		
		http.authorizeHttpRequests((auth)->
		       auth.requestMatchers("/register","/api/users","/api/auth").permitAll()
		       .requestMatchers("/admin").hasRole("ADMIN") // Allows only Admin
		       .requestMatchers("/user").hasRole("USER")   // Allows only User
		       .anyRequest().authenticated());
		
		//.httpBasic(Customizer.withDefaults());// Http Basic Authentication
		//.formLogin(form->form.permitAll()); // Form based Authentication
		
		//Makes the Http request as Stateless
		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		//Adds JwtAuthenticationFilter before UsernamePasswordAuthenticationFilter
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
//	@Bean
//	public AuthenticationProvider authenticationProvider(
//			PasswordEncoder passwordEncoder, 
//			CustomUserDetailsService userDetailsService) {
//		DaoAuthenticationProvider dao = 
//				new DaoAuthenticationProvider(userDetailsService);
//		dao.setPasswordEncoder(passwordEncoder);
//		return dao;
//	}
	
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration configuration) {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
