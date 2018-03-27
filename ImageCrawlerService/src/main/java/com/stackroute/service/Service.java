package com.stackroute.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.domain.Domain;
import com.stackroute.messaging.Producer;

@Component
public class Service {
	

	
	private Producer producer;
@Autowired
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	
private final Logger logger = LoggerFactory.getLogger(this.getClass());	

	  public List<JSONObject> imgCount(Domain incomingMessage){
		  
		  JSONObject obj = new JSONObject();
		  List<JSONObject> objList = new ArrayList<JSONObject>();
		  List<String> urlList= incomingMessage.getUrls();
		for(int i =0 ;i<urlList.size();i++){
		  int count=0;
		  JSONObject jsonObj = new JSONObject();
	 Document doc;
	try {
		doc = Jsoup.connect(urlList.get(i)).userAgent("Mozilla 5.0").get();
		Elements images = doc.getElementsByTag("img");
	     for (Element image : images) { 
	    	 count++; 
	     }
	     try {
	    	 jsonObj.put("ImgCount", count);
	    	 jsonObj.put("url", urlList.get(i));
	    	 jsonObj.put("concept",incomingMessage.getConcept());
	    	 jsonObj.put("domain",incomingMessage.getDomain());
	    	 obj.put("ImgCount",count);
	    	 obj.put("url", urlList.get(i));
	    	 
	    	 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.error("Json error");
		}
	     objList.add(obj);
	     producer.produceMsg(jsonObj);
	     logger.info("Object send for publishing");
			
	}catch (IOException e1) {
 		// TODO Auto-generated catch block
 		System.out.println("Page Not Found");
 		logger.warn("Page not found");
 	}
		}
	
	return objList;
		}


	
}

