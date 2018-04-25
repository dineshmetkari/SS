package com.stackroute.service;



import java.util.ArrayList;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;



@Service
public class WordApiService {


	public ArrayList<String> ApiResults(String term){
		String uri = "http://words.bighugelabs.com/api/1/b9fb9122bf7e43979c005372b3eb65cf/"+term+"/json";	
		String json="";
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<String> synList = new ArrayList<>();
		try{
			json = restTemplate.getForObject(uri, String.class);
			Gson gson =new Gson();
			 synList=gson.fromJson(json, java.util.ArrayList.class);
			 for(int i =0;i<synList.size();i++){
				 if(synList.get(i).contains(" ")){
					 synList.remove(i--);
				 }
			 }
		}
		catch(Exception e){
			System.out.println("The word is not in the external api");
		}
		return synList;
	}
}