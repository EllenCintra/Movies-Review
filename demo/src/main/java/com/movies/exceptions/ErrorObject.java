package com.movies.exceptions;

import java.time.LocalDateTime;

public class ErrorObject {

	private Integer statusCode;
	
	private String message;
	
	private LocalDateTime timestamp;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public LocalDateTime setTimestamp(LocalDateTime timestamp) {
		return this.timestamp = timestamp;
	}
	
}
