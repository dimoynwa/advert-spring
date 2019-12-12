package com.sirma.advertisement.api.config;

import java.text.SimpleDateFormat;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EntityScan(basePackages={"com.sirma.advertisement.api.entity"})
@EnableScheduling
@EnableAsync
public class ApiConfig {

	public static final String DEFAULT_PAGE_NUMBER = "0";
	public static final String DEFAULT_PAGE_SIZE = "30";
	
	public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
	public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);
	
	int MAX_PAGE_SIZE = 50;
	
	
}
