package com.sirma.advertisement.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.sirma.advertisement.api"})
public class AdvertisementApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(AdvertisementApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AdvertisementApplication.class);	
	}

}
