package com.sirma.advertisement.api.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sirma.advertisement.api.config.ApiConfig;
import com.sirma.advertisement.api.model.ChangePasswordRequest;
import com.sirma.advertisement.api.model.UserInfo;
import com.sirma.advertisement.api.security.UserPrincipal;
import com.sirma.advertisement.api.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PreAuthorize("hasRole('USER') OR hasRole('ADVERTISER') OR hasRole('ADMIN')")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping({"", "/"})
	public ResponseEntity<?> allUsers(@RequestParam(value = "page", defaultValue = ApiConfig.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = ApiConfig.DEFAULT_PAGE_SIZE) int size) {
		return ResponseEntity.ok(userService.getAllUsers(page, size));
	}
	
	@GetMapping("/me")
	public ResponseEntity<?> getCurrentUser() {
		Long id = ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@GetMapping("/myAvatar")
	public ResponseEntity<?> getUserImage() throws IOException {
		Long id = ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		
		byte[] bytearr = userService.getUserAvatar(id);
		return ResponseEntity.ok(bytearr);
	}
	
	@PutMapping("/updateMe")
	public ResponseEntity<?> updateMe(@Valid @RequestBody UserInfo body) {
		Long id = ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		return ResponseEntity.ok(userService.updateMyUser(id, body));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
		return ResponseEntity.ok(userService.getUserByUserName(username));
	}
	
	@GetMapping("/{username}/posts")
	public ResponseEntity<?> userPosts(@RequestParam(value = "page", defaultValue = ApiConfig.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = ApiConfig.DEFAULT_PAGE_SIZE) int size, @PathVariable("username") String username) {
		return ResponseEntity.ok(userService.getUserPostsByUsername(page, size, username));
	}
	
	@PutMapping("/updateAvatar")
	public ResponseEntity<?> updateAvatar(@RequestPart("avatar") MultipartFile avatar) throws Exception {
		Long id = ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		return ResponseEntity.ok(userService.updateAvatar(id, avatar));
	}
	
	@PutMapping("/changeMyPassword")
	public ResponseEntity<?> changeMyPassword(@Valid @RequestBody ChangePasswordRequest request) {
		Long id = ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		return ResponseEntity.ok(userService.changePassword(id, request));
	}
}
