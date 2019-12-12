package com.sirma.advertisement.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sirma.advertisement.api.service.ResourcesService;

@RestController
@RequestMapping("api/resources")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PublicController {

	@Autowired
	private ResourcesService resourceService;
	
	@GetMapping("/skillLevels") 
	public ResponseEntity<?> getAllSkillLevels() {
		return ResponseEntity.ok(resourceService.getAllSkillLevels());
	}
	
	@GetMapping("/skills")
	public ResponseEntity<?> getAllSkills() {
		return ResponseEntity.ok(resourceService.getAllSkills());
	}
	
	@GetMapping("/roles")
	public ResponseEntity<?> getAllRoles() {
		return ResponseEntity.ok(resourceService.getAllRoles());
	}
	
}
