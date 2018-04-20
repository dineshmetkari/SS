package com.stackroute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.redisson.Neo4jConceptModel;
import com.stackroute.redisson.Neo4jIntentModel;
import com.stackroute.service.NeoListConceptService;
import com.stackroute.service.NeoListIntentService;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
public class Controller {
	@Autowired
	private Neo4jConceptModel neoListConcept;
	
	@Autowired
	private Neo4jIntentModel neoListIntent;
	
	@Autowired
	private NeoListConceptService neoListConceptService;
	@Autowired
	private NeoListIntentService neoListIntentService;
	
	@GetMapping(value="/neo4jConcept", produces ="application/json")
	public ResponseEntity<Neo4jConceptModel> neoConcept(){

		 neoListConcept = neoListConceptService.neo4jConceptList();
		 
		return new ResponseEntity<Neo4jConceptModel>(neoListConcept,HttpStatus.CREATED);
	
	}
	@GetMapping(value="/neo4jIntent", produces ="application/json")
	public ResponseEntity<Neo4jIntentModel> neoIntent(){

		 neoListIntent = neoListIntentService.neo4jIntentList();
		 
		return new ResponseEntity<Neo4jIntentModel>(neoListIntent,HttpStatus.CREATED);
	
	}

}
