package com.stackroute.domain;

public class Confidence {
	private String domainName;
	private String conceptName;
	private String url;
	private String intent;
	private int imageCount;
	private int videoCount;
	private int codeCount;
	private int counterIndicator;
	private double confidenceScore;

	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getConceptName() {
		return conceptName;
	}

	public void setConceptName(String conceptName) {
		this.conceptName = conceptName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getConfidenceScore() {
		return confidenceScore;
	}

	public void setConfidenceScore(double confidenceScore) {
		this.confidenceScore = confidenceScore;
	}

	public int getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(int videoCount) {
		this.videoCount = videoCount;
	}

	public int getCodeCount() {
		return codeCount;
	}

	public void setCodeCount(int codeCount) {
		this.codeCount = codeCount;
	}

	public int getCounterIndicator() {
		return counterIndicator;
	}

	public void setCounterIndicator(int counterIndicator) {
		this.counterIndicator = counterIndicator;
	}

	public int getImageCount() {
		return imageCount;
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}
}
