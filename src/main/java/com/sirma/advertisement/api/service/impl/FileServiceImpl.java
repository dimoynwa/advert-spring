package com.sirma.advertisement.api.service.impl;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sirma.advertisement.api.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Value("${path.to.images}")
	private String pathToImages;
	
	@Override
	public String storeAvatar(MultipartFile file) throws Exception {
		if(file == null || file.isEmpty()) {
			return null;
		}
		String filePath = pathToImages;
		String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
		File f1 = new File(filePath + "/" + fileName);
		FileUtils.copyInputStreamToFile(file.getInputStream(), f1);
		return fileName;
	}

}
