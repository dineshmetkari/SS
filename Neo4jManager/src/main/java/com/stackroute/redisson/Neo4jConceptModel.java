package com.stackroute.redisson;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Neo4jConceptModel {
	
	private ArrayList<String> domainList;
	private ArrayList<String> conceptList;
	public ArrayList<String> getDomainList() {
		return domainList;
	}

	public void setDomainList(ArrayList<String> domainList) {
		this.domainList = domainList;
	}

	public ArrayList<String> getConceptList() {
		return conceptList;
	}

	public void setConceptList(ArrayList<String> conceptList) {
		this.conceptList = conceptList;
	}


}
