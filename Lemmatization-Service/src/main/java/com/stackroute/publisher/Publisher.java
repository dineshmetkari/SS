package com.stackroute.publisher;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.LemmatizationServiceApplication;

@Component
public class Publisher {

	//private final Logger logger = LoggerFactory.getLogger(this.getClass());
	AmqpTemplate amqpTemplate;

	@Autowired	
	public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	@Autowired
	LemmatizationServiceApplication lemmatizationServiceApplication;

	public String produceMsg(JSONObject json){ 

		amqpTemplate.convertAndSend(lemmatizationServiceApplication.PUBLISH_QUEUE, String.valueOf(json.toString()));
		//logger.info(json.get("url")+" "+json.get("codecount"));
		System.out.println("Published Successfully");
		return "Published Successfully";
	}

}
