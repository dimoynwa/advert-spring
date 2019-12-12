package com.sirma.advertisement.api.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final String error;
    public final String message;
    public final int status;
    public final String date;
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}

	public String getDate() {
		return date;
	}

	public ErrorInfo(Throwable throwable, String message, String i18nKey, HttpStatus status) {
    	this.error = getRootMessage(throwable);
    	this.message = message;
    	this.date = dateFormat.format(new Date());
    	this.status = status.value();
    }
	
	private String getRootMessage(Throwable throwable){
		if(throwable==null){
			return null;
		}
    	String error = throwable.getMessage();
    	Throwable nested = throwable;
    	while(nested.getCause()!=null){
    		nested = nested.getCause();
    		error = nested.getMessage();
    	}
    	return error;
	}

}