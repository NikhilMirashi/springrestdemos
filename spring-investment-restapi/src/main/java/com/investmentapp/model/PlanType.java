package com.investmentapp.model;

public enum PlanType {

	// Mutal,ulif,PPF,FD,RD
	
	mutual("Mutual Fund"),
	ppf("Public Provident Fund"),
	ulif("Unit Limited Investment Plan"),
	senior("Senior Pension Scheme"),
	fd("Fixed Deposit");
	
	public String type;
	
	private PlanType(String planType) {
		this.type=planType;
	}
	
	
	
}
