package com.sirma.advertisement.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sirma.advertisement.api.model.UserInfo;
import com.sirma.advertisement.api.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PutMapping("user/activate/{userId}")
	public ResponseEntity<?> activateUser(@PathVariable("userId") Long userId) {
		UserInfo ui = adminService.activateUser(userId);
		return ResponseEntity.ok(ui);
	}
	
	@PutMapping("user/block/{userId}")
	public ResponseEntity<?> blockUser(@PathVariable("userId") Long userId) {
		UserInfo ui = adminService.blockUser(userId);
		return ResponseEntity.ok(ui);
	}
}
