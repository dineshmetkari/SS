package com.stackroute.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stackroute.domain.Result;
import com.stackroute.listener.MessageListener;
import com.stackroute.publisher.Publisher;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ServiceIT {

	@Autowired
	MessageListener messageListener;
	
	@Autowired
	Result result;
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	Publisher publisher;
	
	@Test
	public void  testImageCount() throws IOException{
		
		try {
			//Arrange
			messageListener.receiveMessage("{\"domain\":\"Java\",\"concept\":\"Algorithm\",\"date\":\"Mar 23, 2018 12:33:51 PM\",\"urls\":[\"https://www.javatpoint.com\"]}");
			Gson gson = new Gson();
			Type type = new TypeToken<Result>() {}.getType();
			
			result = gson.fromJson(messageListener.getMessage(), type);
				
			JSONObject json = new JSONObject();
			json.put("codecount", 0);
			json.put("url", "https://www.javatpoint.com");	
			json.put("concept", "Algorithm");
			json.put("domain", "Java");	
			List<JSONObject> objActual = new ArrayList<JSONObject>() ;
			objActual.add(json);
			//Act
			List<JSONObject> objExpected = codeService.getCodeSnippetCount(result);
			String actual = objActual.get(0).toString();
			String expected=objExpected.get(0).toString();
		
			//Assert
			assertEquals(expected,actual);
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
