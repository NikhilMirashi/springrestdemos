package com.investmentapp.model;

import java.time.LocalDate;
import java.util.List;

public class ApiErrors {

	LocalDate timestamp;
	String message;
	int status;
	List<String> error;

	public ApiErrors() {
		super();
	}

	public ApiErrors(LocalDate timestamp, String message, int status, List<String> error) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.error = error;
	}

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<String> getError() {
		return error;
	}

	public void setError(List<String> error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ApiErrors [timestamp=" + timestamp + ", message=" + message + ", status=" + status + ", error=" + error
				+ "]";
	}
	
	
	
	

}
