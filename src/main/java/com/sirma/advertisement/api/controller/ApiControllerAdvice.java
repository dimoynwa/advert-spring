package com.sirma.advertisement.api.controller;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.sirma.advertisement.api.model.RequestResult;

@RestControllerAdvice("com.sirma.advertisement.api.controller")
public class ApiControllerAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public Object beforeBodyWrite(Object arg0, MethodParameter arg1, MediaType arg2,
			Class<? extends HttpMessageConverter<?>> arg3, ServerHttpRequest arg4, ServerHttpResponse arg5) {
		if (arg0 instanceof RequestResult<?>) {
			return arg0;
		} else {
			RequestResult<Object> result = new RequestResult<>(true);
			result.setPayload(arg0);
			return result;
		}
		
	}

	@Override
	public boolean supports(MethodParameter arg0, Class<? extends HttpMessageConverter<?>> arg1) {
		return true;
	}

}
