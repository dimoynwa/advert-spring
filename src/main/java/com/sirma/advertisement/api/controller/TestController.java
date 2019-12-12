package com.sirma.advertisement.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sirma.advertisement.api.entity.User;
import com.sirma.advertisement.api.repository.UserRepository;

@RestController
@RequestMapping("test")
public class TestController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="notProtected", method=RequestMethod.GET)
	public ResponseEntity<?> notProtected() {
		return ResponseEntity.ok("Not user role");
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value="/needUserRole")
	public ResponseEntity<?> needUserRole() {
		return ResponseEntity.ok("Need user role");
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value="/updateUser")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
		return ResponseEntity.ok(userRepository.save(user));
	}
}
