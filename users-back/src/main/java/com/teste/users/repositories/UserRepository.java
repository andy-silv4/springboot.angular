package com.teste.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.users.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryQuery {

}
