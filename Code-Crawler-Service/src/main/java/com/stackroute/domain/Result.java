package com.stackroute.domain;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Result {
	
	private String domain;

	private String concept;

	private Date date;

	private List<String> urls;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	
	public String toString() {
		return "Result [domain=" + domain + ", concept=" + concept + ", date=" + date + ", urls=" + urls + "]";
	}
	
	

}
