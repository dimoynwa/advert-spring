package com.sirma.advertisement.api.service;

import org.springframework.web.multipart.MultipartFile;

import com.sirma.advertisement.api.model.CreateJobPostRequest;
import com.sirma.advertisement.api.model.JobPostInfo;
import com.sirma.advertisement.api.model.PagedResponse;

public interface JobPostService {

	JobPostInfo createJobPost(Long posterId, CreateJobPostRequest request,  MultipartFile avatar) throws Exception;
	PagedResponse<JobPostInfo> myJobPosts(Long posterId, int page, int size);
	JobPostInfo updateJobPost(Long posterId, Long postId, JobPostInfo jobPost, MultipartFile avatar) throws Exception;
	PagedResponse<JobPostInfo> getAllPosts(int page, int size);
	JobPostInfo getPostById(Long postId);
	JobPostInfo deletePost(Long posterId, Long postId);
}
