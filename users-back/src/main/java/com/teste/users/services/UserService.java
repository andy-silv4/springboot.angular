package com.teste.users.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teste.users.domain.User;
import com.teste.users.repositories.UserFilter;
import com.teste.users.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findById(Integer id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElse(null); 
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public Page<User> findAll(UserFilter userFilter, Pageable pageable) {
		return userRepository.filter(userFilter, pageable);
	}
}
