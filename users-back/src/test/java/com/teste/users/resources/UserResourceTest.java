package com.teste.users.resources;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.teste.users.domain.User;
import com.teste.users.services.UserService;

@ExtendWith(SpringExtension.class)
public class UserResourceTest {
	
	@InjectMocks
	private UserResource userResource;
	
	@Mock
	private UserService userServiceMock;
	
	@BeforeEach
	void setUp() {
		List<User> users = new ArrayList<>();
		
		for (int i = 1; i <= 100; i++) {
			users.add(createUser());			
		}
		
		Page<User> listUsers = new PageImpl<User>(users);
		BDDMockito.when(userServiceMock.findAll(ArgumentMatchers.any(), ArgumentMatchers.any()))
		.thenReturn(listUsers);
	}
	
	@Test
	void test() {
		Page<User> listUsers = userResource.list(null, null);
		
		Assertions.assertThat(listUsers).isNotNull();
		Assertions.assertThat(listUsers.toList())
			.isNotEmpty()
			.hasSize(100);
	}
	
	private User createUser() {
		return new User(null, "user.name", "email@teste.com");	
	}
}
