package com.stackroute.model;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class Neo4jModel {
	
	private ArrayList<String> domainList;
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
	private ArrayList<String> conceptList;

}
