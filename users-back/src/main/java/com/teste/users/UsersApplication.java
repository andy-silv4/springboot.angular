package com.teste.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.teste.users.domain.User;
import com.teste.users.repositories.UserRepository;

@SpringBootApplication
public class UsersApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<User> users = new ArrayList<>();
		
		for (int i = 1; i <= 100; i++) {
			users.add(new User(null, "Usuario " + i, "usuario." + i +"@teste.com"));
		}
		
		userRepository.saveAll(users);
	}

}
