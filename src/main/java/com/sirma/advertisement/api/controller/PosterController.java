package com.sirma.advertisement.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sirma.advertisement.api.config.ApiConfig;
import com.sirma.advertisement.api.model.CreateJobPostRequest;
import com.sirma.advertisement.api.model.JobPostInfo;
import com.sirma.advertisement.api.security.UserPrincipal;
import com.sirma.advertisement.api.service.JobPostService;

@RestController
@RequestMapping("/api/poster/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PreAuthorize("hasRole('ADVERTISER')")
public class PosterController {

	@Autowired
	private JobPostService jobPostService;
	
	@PostMapping(value="/posts", consumes = {"multipart/form-data"})
	public ResponseEntity<?> createJobPost(@Valid @RequestPart("createPostRequest") CreateJobPostRequest request, @RequestPart("avatar") MultipartFile avatar) throws Exception {
		Long id = ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		return ResponseEntity.ok(jobPostService.createJobPost(id, request, avatar));
	}
	
	@GetMapping("/posts/my")
	public ResponseEntity<?> myJobPosts(@RequestParam(value = "page", defaultValue = ApiConfig.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = ApiConfig.DEFAULT_PAGE_SIZE) int size) {
		Long id = ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		return ResponseEntity.ok(jobPostService.myJobPosts(id, page, size));
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<?> updatePost(@PathVariable("postId") Long postId, @Valid @RequestPart("jobPost") JobPostInfo post, @RequestPart("avatar") MultipartFile avatar) throws Exception {
		Long userId = ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		return ResponseEntity.ok(jobPostService.updateJobPost(userId, postId, post, avatar));
	}
	
	@GetMapping("/posts/")
	public ResponseEntity<?> getAllPosts(@RequestParam(value = "page", defaultValue = ApiConfig.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = ApiConfig.DEFAULT_PAGE_SIZE) int size) {
		return ResponseEntity.ok(jobPostService.getAllPosts(page, size));
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<?> getPostById(@PathVariable("postId") Long postId) {
		return ResponseEntity.ok(jobPostService.getPostById(postId));
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable("postId") Long postId) {
		Long posterId = ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		return ResponseEntity.ok(jobPostService.deletePost(posterId, postId));
	}
}
