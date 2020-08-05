package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TcUser;

@Repository
public interface TcUserRepository extends JpaRepository<TcUser, Long> {
	
	Optional<TcUser> findByUsername(String username);

}
