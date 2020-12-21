package com.teste.users;

import java.text.SimpleDateFormat;
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
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		
		for (int i = 1; i <= 100; i++) {
			users.add(new User(i, "u."+i+".user", "password", true, date.parse("31/12/2020"), "Usuário", "da Silva", "usuario." + i +"@teste.com", "3199999999"));
		}
		
		userRepository.saveAll(users);
	}

}
