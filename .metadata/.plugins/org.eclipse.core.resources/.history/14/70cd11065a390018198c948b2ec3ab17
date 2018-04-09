package com.stackroute.service;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import com.stackroute.model.FetchUrl;
import com.stackroute.model.UserInput;


public class FetchServiceIntegrationTest {

	private FetchService fetchService = new FetchService();

//	@Before
//	public void setUpMock() {
//		Driver driver= GraphDatabase.driver("bolt://172.23.238.165",AuthTokens.basic("neo4j","password"));		
//	}

	
	@Test
	public void test() {
//		Arrange
		UserInput userInput = new UserInput();
		userInput.setConcept("Ajax");
		userInput.setIntent("Advance");
		String Query= "Match(n:url)-[x:"+userInput.getIntent()+"]->(c:concept{name:\""+userInput.getConcept()+"\""+"})return n.imgCount as imgCount,n.videoCount as videoCount,n.codeCount as codeCount,n.url as url,n.counterIndicator as counterIndicator, x.confidenceScore as confidenceScore";	
		ArrayList<Double> expected = new ArrayList<>();
		ArrayList<Double> actual = new ArrayList<>();
		
		expected.add(202.3157894736842);
		expected.add(133.5486381322957);
		expected.add(122.88082901554404);
		expected.add(122.59475218658892);
		expected.add(17.066666666666666);
		expected.add(2.749403341288783);
		expected.add(2.4123076923076923);
		expected.add(0.9655172413793103);
		expected.add(0.0);

	
	
//		Act
		ArrayList<FetchUrl> actualList = fetchService.fetchedUrl(Query);
		for(int i=0; i<actualList.size();i++){
			actual.add(actualList.get(i).getConfidenceScore());
		}
		
		
//		Asserts
		String result = actual.toString();
		String expectResult = expected.toString();
		assertEquals(result,expectResult);
	}

}