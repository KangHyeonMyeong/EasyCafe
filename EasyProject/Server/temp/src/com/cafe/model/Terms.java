package com.cafe.model;

public class Terms {
	
	private String term;
	private String policy;
	private String agreement;
	
	public Terms(String terms, String policy, String agreement) {
		this.term = terms;
		this.policy = policy;
		this.agreement = agreement;
	}

	public String getTerm() {
		return term;
	}

	public String getPolicy() {
		return policy;
	}

	public String getAgreement() {
		return agreement;
	}

}
