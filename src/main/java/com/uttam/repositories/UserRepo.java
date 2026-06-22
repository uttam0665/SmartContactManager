package com.uttam.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uttam.entities.User;
import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User, String>{

	//extra method  db relatedoperations
	//custom query methods
	// custom finder method

	//custom find by email
	Optional<User> findByEmail(String email);

	//custom find by email and password
	Optional<User> findByEmailAndPassword(String email, String password);
	
	
}
