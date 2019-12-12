package com.sirma.advertisement.api.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

public class RequestResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final RequestResult<?> EMPTY = new RequestResult<Object>();
	
	private boolean success;
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private ErrorInfo errorInfo;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T payload;
	
	public RequestResult() {
		super();
	}

	public RequestResult(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}
	
}