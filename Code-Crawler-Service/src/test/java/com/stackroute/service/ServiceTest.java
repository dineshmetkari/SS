package com.stackroute.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.stackroute.domain.Result;
import com.stackroute.publisher.Publisher;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(CodeService.class)
public class ServiceTest {
	
	private CodeService codeService;
	
	private Result result;
	
	@Mock
	Publisher publisher;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		codeService = new CodeService();
		codeService.setPublisher(publisher);
		result = new Result();
		List<String> urls = new ArrayList<String>();
		urls.add("https://docs.oracle.com/javase/1.4.2/docs");
		result.setUrls(urls);
		result.setConcept("2");
		result.setDomain("Java");
		result.setDate(null);
	}
	
	@Test
	public void codeCountTest() throws IOException {
		//Arrange
		List<String> urls = new ArrayList<String>();
		urls.add("https://docs.oracle.com/javase/1.4.2/docs");
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject();
		try {
			json.put("codecount", 1);
			json.put("url", "https://docs.oracle.com/javase/1.4.2/docs");	
			json.put("concept", "2");
			json.put("domain", "Java");	
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
		
		jsonList.add(json);
		
		List<JSONObject> jsonListExpected = codeService.getCodeSnippetCount(result);
		//Assert
		
		System.out.println(jsonListExpected.toString());
		System.out.println(jsonList.toString());

		assertThat(jsonListExpected.toString(), is(equalTo(jsonList.toString())));
	}

}
