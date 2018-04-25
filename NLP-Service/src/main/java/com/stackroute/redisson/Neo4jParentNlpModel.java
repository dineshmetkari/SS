package com.stackroute.redisson;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Neo4jParentNlpModel {
	
	private List<String> parentNodes;

	public List<String> getParentNodes() {
		return parentNodes;
	}

	public void setParentNodes(List<String> parentNodes) {
		this.parentNodes = parentNodes;
	}
	
	
}
