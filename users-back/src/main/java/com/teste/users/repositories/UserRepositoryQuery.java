package com.teste.users.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.teste.users.domain.User;

public interface UserRepositoryQuery {
	
	public Page<User> filter(UserFilter userFilter, Pageable pageable);

}
