package com.example.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TcUser;
import com.example.demo.repository.TcUserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class TcUserController {
	
	@Autowired
	TcUserRepository tcUserRepository;
	
	@PutMapping("/add")
	public void setUser(@Valid @RequestBody TcUser tcUser) {
		tcUserRepository.save(tcUser);
	}
	
	@GetMapping("{userId}")
	public TcUser getUserById(@PathVariable(value = "userId") Long userId) {
		Optional<TcUser> iu = tcUserRepository.findById(userId);
		if (!iu.isEmpty()) {
			return null;
		} else {
			return iu.get();
		}
	}

}
