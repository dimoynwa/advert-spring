package com.sirma.advertisement.api.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig  extends WebMvcConfigurationSupport {

	@Bean
	public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
		System.out.println("Going to create ByteArrayHttpMessageConverter");
		ByteArrayHttpMessageConverter converter = new ByteArrayHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG));
		return converter;
	}
	
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
       converters.add(new MappingJackson2HttpMessageConverter());
       converters.add(byteArrayHttpMessageConverter());
       converters.add(new BufferedImageHttpMessageConverter());
    }
}
