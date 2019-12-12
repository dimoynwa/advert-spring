package com.sirma.advertisement.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String resourceName;
    private String fieldName;
    private Object fieldValue;
    
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
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

	public static ResourceNotFoundException userByIdNotFound(Long userId) {
		return new ResourceNotFoundException("User", "id", userId);
	}
	
	public static ResourceNotFoundException userByEmailNotFound(String email) {
		return new ResourceNotFoundException("User", "email", email);
	}
	
	public static ResourceNotFoundException userByUsername(String username) {
		return new ResourceNotFoundException("User", "username", username);
	}
	
	public static ResourceNotFoundException roleByNameNotFound(String roleName) {
		return new ResourceNotFoundException("Role", "name", roleName);
	}
	
	public static ResourceNotFoundException passwordResetLinkNotFound(String token) {
		return new ResourceNotFoundException("Password reset link", "token", token);
	}
	
	public static ResourceNotFoundException jobPostByIdNotFound(Long id) {
		return new ResourceNotFoundException("Job post", "id", id);
	}
	
}
