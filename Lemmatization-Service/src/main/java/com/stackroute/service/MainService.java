package com.stackroute.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.domain.Intent;

@Service
public class MainService {

	@Autowired
	ConceptService conceptService;

	static String conceptFound = "";

	static int flag = 0;

	static String prevStr = "";

	static String currStr = "";

	static Set<String> conceptsFound = new HashSet<String>();

	public void getConceptAndIntent(List<String> query){

		List<String> queryList = new ArrayList<String>();
		String nouns = "";
		String notNouns = "";
		for(String word:query){
			if(word.contains("NN")){
				String[] words = word.split("\\s+");
				//System.out.println(Arrays.toString(words));
				for(String str:words){
					//System.out.println(str);
					int index = str.indexOf("/");
					queryList.add(str.substring(0, index));
					str = str.toLowerCase();
					str = str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
					nouns += str.substring(0, index) + " ";
				}
			}
			else{
				int index = word.indexOf("/");
				queryList.add(word.substring(0, index));
				notNouns += word.substring(0, index) + " ";
			}
		}

		String concept = getConcept(nouns.trim());
		
		List<String> finalConcepts = new ArrayList<String>();


		for(String str1 : conceptsFound){
			int check = 0;
			for(String str2 : conceptsFound){
				if(!str1.equals(str2) && str2.contains(str1))
					check = 1;
			}
			if(check==0)
				finalConcepts.add(str1);
		}

		
		String intent = getIntent(notNouns);
		
		if(finalConcepts.isEmpty() && intent.equals("IN"))
			System.out.println("CNIN" + "parent nodes");
		else if(!finalConcepts.isEmpty() && intent.equals("IN"))
			System.out.println("CYIN" + "Do you mean Beginner/Intermediate/Advanced of "
					+ "" + finalConcepts.toString());
		else if(finalConcepts.isEmpty() && !intent.equals("IN"))
			System.out.println("CNIN" + "parent nodes");
		else if(!finalConcepts.isEmpty() && !intent.equals("IN")){
			System.out.println("CYIY");
			System.out.println("Concept: " + finalConcepts.toString());
			System.out.println("Intent: " + intent);
		}

	}

	public static String getConcept(String nouns){
		//List<String> concepts = conceptService.fetchConcepts();
		currStr = nouns;
		List<String> concepts = new ArrayList<String>();
		concepts.add("Spring Boot");
		concepts.add("Spring");
		concepts.add("app instances");
		concepts.add("instances");

		if(flag == 0 && prevStr == currStr)
			return null;


		else{

			for(String concept : concepts)
			{
				if(concept.equals(nouns)){

					conceptFound = concept;
					flag = 1;
					System.out.println("concept found" + conceptFound);
					conceptsFound.add(conceptFound);

				}
			}
			if(flag == 0){
				System.out.println("//"+nouns+"ff");
				if(nouns.contains(" ")){
					//System.out.println(nouns.substring(0, nouns.lastIndexOf(" ")));
					prevStr = nouns;
					getConcept(nouns.substring(0, nouns.lastIndexOf(" ")).trim());
					String [] arr = nouns.split(" ", 2);	
					//System.out.println(arr[1]+"sss");//System.out.println(conceptFound);
					prevStr = nouns;
					getConcept(arr[1]);
				}
				else{
					prevStr = nouns;
					getConcept(nouns);
				}

			}
		}
		flag = 0;
		concepts.add(conceptFound);

		return conceptFound;

		//System.out.println(conceptFound);


	}

	public String getIntent(String notNouns){
		List<Intent> intents = new ArrayList<Intent>();
		
//		int beginnerSum = 0;
//		int intermediateSum = 0;
//		int advancedSum = 0;
//		int illustrationSum = 0;

		Intent intent1 = new Intent();

		intent1.setIntent("Beginner");
		intent1.setName("how");
		intent1.setWeight("9");

		Intent intent2 = new Intent();

		intent2.setIntent("Intermediate");
		intent2.setName("define");
		intent2.setWeight("7");

		Intent intent3 = new Intent();

		intent3.setIntent("Advance");
		intent3.setName("analyze");
		intent3.setWeight("8");


		intents.add(intent1);
		intents.add(intent2);
		intents.add(intent3);
		
		String[] intentArray = notNouns.split("\\s+");
		Map<String,Integer> weights = new HashMap<String,Integer>();
		
		for(String word : intentArray)
		{
			int sum = 0;
			for(Intent intent : intents)
			{
				if(word.equals(intent.getName()))
				{
					if(intent.getIntent().equals("Beginner")){
						
						if(weights.containsKey("Beginner")){
							sum = weights.get("Beginner");
							sum += Integer.parseInt(intent.getWeight());
							weights.put("Beginner", sum);
						}else{
							weights.put("Beginner", Integer.parseInt(intent.getWeight()));
						}
					}
					
					else if(intent.getIntent().equals("Intermediate")){
						
						if(weights.containsKey("Intermediate")){
							sum = weights.get("Intermediate");
							sum += Integer.parseInt(intent.getWeight());
							weights.put("Intermediate", sum);
						}else{
							weights.put("Intermediate", Integer.parseInt(intent.getWeight()));
						}
					}
					
					else if(intent.getIntent().equals("Advance")){
						if(weights.containsKey("Advance")){
							sum = weights.get("Advance");
							sum += Integer.parseInt(intent.getWeight());
							weights.put("Advance", sum);
						}else{
							weights.put("Advance", Integer.parseInt(intent.getWeight()));
						}
					}
					
					else if(intent.getIntent().equals("Illustration")){
						if(weights.containsKey("Illustration")){
							sum = weights.get("Illustration");
							sum += Integer.parseInt(intent.getWeight());
							weights.put("Illustration", sum);
						}else{
							weights.put("Illustration", Integer.parseInt(intent.getWeight()));
						}
					}
						
				}
			}	
			
		}
		
		
		int max = 0;
		String intent = "";
		Set keys = weights.keySet();
		Iterator a = keys.iterator();
		
		while(a.hasNext()) {
			String key = (String)a.next();
			int value = weights.get(key);
			if(value>max){
				max = value;
				intent = key;
			}
		}

		if(max==0)
			return "IN";
		else
			return intent;
	}

//	public static void main(String[] args){
//		MainService mainService = new MainService();
//		mainService.getConcept("app instances Spring Boot works");
//
//
//
//		
//		
//		System.out.println(mainService.getIntent("how define analyse"));
//	}


}