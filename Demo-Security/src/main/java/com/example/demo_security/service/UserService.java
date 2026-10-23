package com.example.demo_security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo_security.entity.User;
import com.example.demo_security.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User saveUser(User user) {
		user.setRole("USER");
		String hashPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashPassword);
		return userRepository.save(user);
	}
}
