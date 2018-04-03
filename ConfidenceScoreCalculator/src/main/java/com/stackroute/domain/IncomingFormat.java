package com.stackroute.domain;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class IncomingFormat {
	private String domain;
	private String concept;
	private String url;
	private int videoCount;
	private int imgCount;
	private int codeCount;
	private JSONObject terms;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getVideoCount() {
		return videoCount;
	}
	public void setVideoCount(int videoCount) {
		this.videoCount = videoCount;
	}
	public int getImgCount() {
		return imgCount;
	}
	public void setImgCount(int imgCount) {
		this.imgCount = imgCount;
	}
	public int getCodeCount() {
		return codeCount;
	}
	public void setCodeCount(int codeCount) {
		this.codeCount = codeCount;
	}
	public JSONObject getTerms() {
		return terms;
	}
	public void setTerms(JSONObject terms) {
		this.terms = terms;
	}

}
