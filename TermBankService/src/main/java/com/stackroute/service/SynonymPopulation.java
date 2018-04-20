package com.stackroute.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.stackroute.redisson.IntentModel;
import com.stackroute.redisson.Neo4jIntentModel;


@Service
public class SynonymPopulation  implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	WordApiService wordApiService ;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		Config config = new Config();
		config.useSingleServer().setAddress("redis://localhost:6379");
		RedissonClient redisson = Redisson.create(config);
		RBucket<Neo4jIntentModel> bucket = redisson.getBucket("intentModel");
		ArrayList<IntentModel> termList = bucket.get().getIntentList();
		
		ArrayList<Map<String,ArrayList<String>>> intentTermList = new ArrayList<>();
		Map<String,ArrayList<Map<String,ArrayList<String>>>> synonymMap = new HashMap<>();
		
		for(int i=0;i<termList.size();i++){
			IntentModel intentModel = termList.get(i);
			String intent=intentModel.getIntent();
			String term = intentModel.getTerm();
			 ArrayList<String> synonyms = wordApiService.ApiResults(term);
			 System.out.println(synonyms.toString());
			 Map<String,ArrayList<String>> termMap = new HashMap<>();
			termMap.put(term, synonyms);
			if(synonymMap.containsKey(intent)){
				ArrayList<Map<String,ArrayList<String>>> tempList = synonymMap.get(intent);
				tempList.add(termMap);
				synonymMap.put(intent, tempList);
			}else{
				ArrayList<Map<String,ArrayList<String>>> tempIntentList = new ArrayList<>();
				tempIntentList.add(termMap);
				synonymMap.put(intent, tempIntentList);
			}
		}
		Gson gson = new Gson();
		String json = gson.toJson(synonymMap);
		System.out.println(json);

	}

}
