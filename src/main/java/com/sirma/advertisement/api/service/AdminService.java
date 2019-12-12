package com.sirma.advertisement.api.service;

import com.sirma.advertisement.api.model.UserInfo;

public interface AdminService {

	UserInfo activateUser(Long userId);
	
	UserInfo blockUser(Long userId);
}
