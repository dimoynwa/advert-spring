package com.sirma.advertisement.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String resourceName;
    private String fieldName;
    private Object fieldValue;
    
	public ResourceAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
		super(String.format("%s with %s : '%s' already exists", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	public static ResourceAlreadyExistsException userByUserName(String userName) {
		return new ResourceAlreadyExistsException("User", "username", userName);
	}
	
	public static ResourceAlreadyExistsException userByEmail(String email) {
		return new ResourceAlreadyExistsException("User", "email", email);
	}
}
