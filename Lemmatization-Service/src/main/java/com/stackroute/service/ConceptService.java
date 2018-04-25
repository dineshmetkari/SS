package com.stackroute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.repository.ConceptRepository;

@Service
public class ConceptService {
	
	private ConceptRepository conceptRepository;

	@Autowired
	public void setConceptRepository(ConceptRepository conceptRepository) {
		this.conceptRepository = conceptRepository;
	}
	
	@Transactional(readOnly = true)
	public List<String> fetchConcepts(){
		return conceptRepository.getConcepts();
	}
	
	@Transactional(readOnly = true)
	public List<String> fetchIntents(){
		return conceptRepository.getIntents();
	}
	
	

}
