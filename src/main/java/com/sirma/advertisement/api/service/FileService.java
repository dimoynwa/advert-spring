package com.sirma.advertisement.api.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String storeAvatar(MultipartFile file) throws Exception;
	
}
