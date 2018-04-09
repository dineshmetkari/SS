package com.stackroute.controller;

import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.model.FetchUrl;
import com.stackroute.model.UserInput;
import com.stackroute.service.FetchService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
public class Controller {
	@Autowired
	private FetchService fetchService;


	@Autowired
	UserInput userInput;
	// FetchService fetchService=new FetchService("bolt://172.23.238.165",
	// "neo4j", "password");;
	

//	@Value("${uri}")
//	String uri;
//	@Value("${user}")
//	String user;
//	@Value("${password}")
//	String password;


	@GetMapping(value = "/user", produces = "application/json")
	public ResponseEntity<ArrayList<FetchUrl>> addUser(@RequestParam("domain") final String domain,
			@RequestParam("concept") final String concept, @RequestParam("intent") final String intent) {
		userInput.setConcept(concept);
		userInput.setDomain(domain);
		userInput.setIntent(intent);
		String Query = "Match(n:url)-[x:" + userInput.getIntent() + "]->(c:concept{name:\"" + userInput.getConcept()
		+ "\""
		+ "})return n.imgCount as imgCount,n.videoCount as videoCount,n.codeCount as codeCount,n.url as url,n.counterIndicator as counterIndicator, x.confidenceScore as confidenceScore";
		System.out.println(Query);
		final ArrayList<FetchUrl> fetchedUrls = fetchService.fetchedUrl(Query);
System.out.println(fetchedUrls.toString());
		return new ResponseEntity<ArrayList<FetchUrl>>(fetchedUrls, HttpStatus.CREATED);

	}

}