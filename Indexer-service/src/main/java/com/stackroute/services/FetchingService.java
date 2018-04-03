package com.stackroute.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.stackroute.domain.Confidence;

@Service
public class FetchingService {
	@Autowired
	Gson gson;
	@Value("${uriofneo}")
	private String neo4juri;
	@Value("${spring.data.neo4j.username}")
	private String username;
	@Value("${spring.data.neo4j.password}")
	private String password;

	public void fetch(String json) {
		Confidence confidence = gson.fromJson(json, Confidence.class);

		ExecutingService.executeQuery(confidence, neo4juri, username, password);

	}
}
