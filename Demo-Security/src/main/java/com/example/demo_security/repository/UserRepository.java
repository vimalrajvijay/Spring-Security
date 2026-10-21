package com.example.demo_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo_security.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
}
