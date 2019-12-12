package com.sirma.advertisement.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sirma.advertisement.api.exception.ResourceNotFoundException;
import com.sirma.advertisement.api.model.ErrorInfo;
import com.sirma.advertisement.api.model.RequestResult;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ErrorInfo errorInfo = new ErrorInfo(ex, (body != null) ? body.toString() : null, null, status);
		RequestResult<Object> result = new RequestResult<>(false);
		result.setErrorInfo(errorInfo);
		return new ResponseEntity<Object>(result, headers, status);
	}

	@ExceptionHandler({ ResourceNotFoundException.class })
	protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
		return handleExceptionInternal(ex, "Request parameters not valid.", new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleInternal(final Exception ex, final WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}
	
}
