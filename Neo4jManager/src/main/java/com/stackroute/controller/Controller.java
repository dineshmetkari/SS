package com.stackroute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.redisson.Neo4jConceptModel;
import com.stackroute.redisson.Neo4jConceptNlpModel;
import com.stackroute.redisson.Neo4jIntentModel;
import com.stackroute.redisson.Neo4jParentNlpModel;
import com.stackroute.redisson.Neo4jUrlModel;
import com.stackroute.service.NeoListConceptNlpService;
import com.stackroute.service.NeoListConceptService;
import com.stackroute.service.NeoListIntentService;
import com.stackroute.service.NeoListParentNlpService;
import com.stackroute.service.NeoMapUrlService;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
public class Controller {
	@Autowired
	private Neo4jConceptModel neoListConcept;
	
	@Autowired
	private Neo4jIntentModel neoListIntent;
	
	@Autowired
	private Neo4jUrlModel neoListUrl;
	
	@Autowired
	private Neo4jConceptNlpModel neoListConceptNlp;
	@Autowired
	private Neo4jParentNlpModel neoListParentNlp;
	
	@Autowired
	private NeoListConceptService neoListConceptService;
	@Autowired
	private NeoListIntentService neoListIntentService;
	@Autowired
	private NeoMapUrlService neoMapUrlService;
	@Autowired
	private NeoListConceptNlpService neoConceptNlpService;
	@Autowired
	private NeoListParentNlpService neoParentNlpService;
	
	
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
	@GetMapping(value="/neo4jUrl", produces ="application/json")
	public ResponseEntity<Neo4jUrlModel> neoUrl(){

		 neoListUrl = neoMapUrlService.neo4jUrlMap();
		 
		return new ResponseEntity<Neo4jUrlModel>(neoListUrl,HttpStatus.CREATED);
	
	}
	@GetMapping(value="/neo4jConceptNlp", produces ="application/json")
	public ResponseEntity<Neo4jConceptNlpModel> neoConceptNlp(){

		 neoListConceptNlp = neoConceptNlpService.neo4jConceptList();
		 
		return new ResponseEntity<Neo4jConceptNlpModel>(neoListConceptNlp,HttpStatus.CREATED);
	
	}

	@GetMapping(value="/neo4jParentNlp", produces ="application/json")
	public ResponseEntity<Neo4jParentNlpModel> neoParentNlp(){

		neoListParentNlp = neoParentNlpService.neo4jParentList();
		 
		return new ResponseEntity<Neo4jParentNlpModel>(neoListParentNlp,HttpStatus.CREATED);
	
	}

}
