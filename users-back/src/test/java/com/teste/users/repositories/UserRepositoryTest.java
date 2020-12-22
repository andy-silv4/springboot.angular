package com.teste.users.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.teste.users.domain.User;

@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	void findAll_ReturnsListOfUsers_WhenSuccessful() {
		List<User> users = new ArrayList<>();
		
		for (int i = 1; i <= 100; i++) {
			users.add(createUser());			
		}
		
		User newUser = createUser();
		newUser.setEmail("new.email@.teste.com");
		
		users.add(newUser);
	
		List<User> usersSaved = userRepository.saveAll(users);
		
		Assertions.assertThat(usersSaved).isNotEmpty();
		Assertions.assertThat(usersSaved.size()).isEqualTo(101);
		Assertions.assertThat(users).contains(newUser);
	}
	
	@Test
	void findById_ReturnsUser_WhenSuccessful() {
		User userToBeSaved = createUser();
		User userSaved = this.userRepository.save(userToBeSaved);
		
		User user = userRepository.findById(userSaved.getId()).orElse(null);
		
		Assertions.assertThat(user).isNotNull();
		Assertions.assertThat(user.getId()).isNotNull();
		Assertions.assertThat(user.getName()).isEqualTo(userSaved.getName());
	}
	
	@Test
	void findById_ReturnsEmpty_WhenNotFound() {
		Optional<User> user = userRepository.findById(0);
		
		Assertions.assertThat(user).isEmpty();
	}
	
	@Test
	void save_PersistUser_WhenSuccessful() {
		User userToBeSaved = createUser();
		User userSaved = this.userRepository.save(userToBeSaved);
		
		Assertions.assertThat(userSaved).isNotNull();
		Assertions.assertThat(userSaved.getId()).isNotNull();
		Assertions.assertThat(userSaved.getName()).isEqualTo(userToBeSaved.getName());
		Assertions.assertThat(userSaved.getEmail()).isEqualTo(userToBeSaved.getEmail());
	}
	
	private User createUser() {
		return new User(null, "user.name", "email@teste.com");	
	}
}
