package com.userdemo.repository;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.userdemo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query("SELECT u FROM User u WHERE u.username = ?1")
	public User findByUsername(String username);

	@Query("SELECT u FROM User u WHERE u.api_token = ?1")
	public User findByApitoken(String api_token);
}
