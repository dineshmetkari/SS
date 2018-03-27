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
import org.springframework.stereotype.Service;

import com.stackroute.domain.Result;
import com.stackroute.publisher.Publisher;

@Service
public class CodeService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	Publisher publisher;

	@Autowired
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public List<JSONObject> getCodeSnippetCount(Result result){
		
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		
		try {	
			List<String> urls = result.getUrls();
			for(String url : urls){
				JSONObject json = new JSONObject();
				if(getCount(url)!=-1)
					json.put("codecount", getCount(url));
				else 
					continue;
				json.put("url", url);	
				json.put("concept", result.getConcept());
				json.put("domain", result.getDomain());	
				publisher.produceMsg(json);	
				jsonList.add(json);
			}
			return jsonList;
		}  
		catch (JSONException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		return jsonList;

	}
	
	public int getCount(String url){	
		try {
			Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
			Elements codes = doc.select("code");
			Elements pres = doc.select("pre");
			int preCount = 0;
			for(Element pre : pres){
				preCount++;
			}
			int codeCount = 0;
			for(Element code : codes){
				codeCount++;
			}
			return preCount+codeCount;
			
		} 
		catch (IOException e) {
			System.err.println("Page Not Found");
			logger.error(e.getMessage());
		}
		return -1;
		
		//return 0;
		
	}
	
	

}
