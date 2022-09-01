package com.investmentapp.exceptions;

public class PlanNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PlanNotFoundException() {
		super();
	}

	public PlanNotFoundException(String message) {
		super(message);
	}
	
	
}
