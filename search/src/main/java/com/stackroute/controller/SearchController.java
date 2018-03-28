package com.stackroute.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.domain.SearchQuery;
import com.stackroute.domain.SearchResult;
import com.stackroute.services.SearchResultService;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;

/**
 * controller class with five methods-
 * 
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
/**
 * 
 * SearchController class should call the takeQuery method of the SearchResultService
 * interface and return the objects to the client
 * 
 * @author
 *
 */
public class SearchController {

	/**
	 * An object of SearchResultService interface declared to be used as a
	 * reference for its implemented class
	 */
	SearchResultService searchResultService;

	@Autowired
	/**
	 * setter for the SearchResultService type object (either
	 * SearchResultServiceImpl 
	 * 
	 * @param SearchResultService
	 */
	public void setSearchResultService(final SearchResultService searchResultService) {
		this.searchResultService = searchResultService;
	}

	@PostMapping(value = "/search", produces = "application/json")
	/**
	 * This takeQuery method is calling another takeQuery method from
	 * SearchResultService to add the searchResult object in the database
	 * 
	 * @param SearchQuery
	 * @return SearchResult
	 */
	public ResponseEntity<List<SearchResult>> takeQuery(@RequestBody final SearchQuery searchQuery) {
		final List<SearchResult> processedResult = searchResultService.takeQuery(searchQuery);

		return new ResponseEntity<List<SearchResult>>(processedResult, HttpStatus.CREATED);

	}

}
