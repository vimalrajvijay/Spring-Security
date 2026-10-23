package com.example.demo_security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo_security.entity.User;
import com.example.demo_security.repository.UserRepository;

@Service
public class CustomUserDetailsService 
            implements UserDetailsService{
	private UserRepository userRepository;
	public CustomUserDetailsService(
			UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		return org.springframework.security.core
				.userdetails.User
		.withUsername(user.getUsername())
		.password(user.getPassword())
		.roles(user.getRole())
		.build();
	}

}
