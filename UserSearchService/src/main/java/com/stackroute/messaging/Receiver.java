package com.stackroute.messaging;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stackroute.model.UserInput;
import com.stackroute.model.UserOutput;
import com.stackroute.redisson.FetchUrl;
import com.stackroute.service.FetchNeoUrl;

@Component
public class Receiver {
	

	@Autowired
	private FetchNeoUrl fetchNeoUrl;
	@Autowired
	private UserInput userInput;
	@Autowired
	private Publish publish;
	@Autowired
	private UserOutput output;
	
	private String json;
	
	 public  void receiveMessage(String message) {
	        System.out.println("Received <" + message + ">");
			String fetchedMessage=message;
			
			Gson gson = new Gson();
			Type type = new TypeToken<UserInput>() {}.getType();
			
				userInput = gson.fromJson(fetchedMessage, type);
				String domain = userInput.getDomain();
				String concept = userInput.getConcept();
				String intent =userInput.getIntent();
				String sessionId = userInput.getSessionId();
				String type1 = userInput.getType();
				boolean illustration = userInput.isIllustration();
				
				System.out.println(domain + " " + concept +" "+intent);
				ArrayList<FetchUrl> fetchList	=fetchNeoUrl.fetchedUrl(domain, concept, intent,illustration);
	
				
				if(fetchList.isEmpty()){
					json = "{\"message\":\"Urls not populated yet\",\"concept\":\""+concept+"\",\"domain\":\""+domain+"\"}";
					output.setMessage(json);
					output.setSessionId(sessionId);
					output.setType(type1);
					output.setResponse(fetchList);
					output.setIllustration(illustration);
				
				}else{
						output.setResponse(fetchList);
						output.setSessionId(sessionId);
						output.setType(type1);
						output.setMessage("Good to Go");
						output.setIllustration(illustration);
					} 
					 
				String jsonInString = gson.toJson(output);
				try {
						publish.publishMsg(jsonInString);
					} catch (AmqpException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
					
			
					
	    }
}