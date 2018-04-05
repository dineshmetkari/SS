package com.stackroute.model;

import org.springframework.stereotype.Component;

@Component
public class UserInput {
	
	private String domain;
	private String concept;
	private String intent;
	
	
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getConcept() {
		return concept;
	}
	public void setConcept(String concept) {
		this.concept = concept;
	}

	

}
