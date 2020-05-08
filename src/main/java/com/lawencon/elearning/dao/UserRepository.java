package com.lawencon.elearning.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.Account;

@Repository
public interface UserRepository extends JpaRepository<Account, String> {
	
	Optional<Account> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Account findByEmail(String username); 
}
