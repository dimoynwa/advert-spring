package com.sirma.advertisement.api.service;

import org.springframework.web.multipart.MultipartFile;

import com.sirma.advertisement.api.model.ChangePasswordRequest;
import com.sirma.advertisement.api.model.JobPostInfo;
import com.sirma.advertisement.api.model.PagedResponse;
import com.sirma.advertisement.api.model.SignUpRequest;
import com.sirma.advertisement.api.model.UserInfo;

public interface UserService {
	
	UserInfo registerUser(SignUpRequest request, MultipartFile avatar) throws Exception;
	
	PagedResponse<UserInfo> getAllUsers(int page, int size);
	
	UserInfo getUserById(Long id);
	
	byte[] getUserAvatar(Long userId);
	
	UserInfo updateMyUser(Long id, UserInfo body);
	
	UserInfo changePassword(Long id, ChangePasswordRequest request);

	void checkUsername(String username);

	void checkEmail(String email);

	UserInfo updateAvatar(Long id, MultipartFile avatar) throws Exception;

	UserInfo getUserByUserName(String username);

	PagedResponse<JobPostInfo> getUserPostsByUsername(int page, int size, String username);
}
