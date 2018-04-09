package com.stackroute.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.stackroute.model.FetchUrl;
import com.stackroute.model.UserInput;


@Service
public class FetchService implements AutoCloseable {

	private  Driver driver;

	@Value("${uri}")
	String uri="bolt://172.23.238.165";;
	@Value("${user}")
	String user= "neo4j";
	@Value("${password}")
	String password="password";

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		driver.close();
	}
	public ArrayList<FetchUrl> fetchedUrl(String Query){
		
		driver = GraphDatabase.driver(uri,AuthTokens.basic(user, password));
		ArrayList<FetchUrl> fetchList = new ArrayList<>();
		Session session = driver.session();
		String Greeting= session.writeTransaction(new TransactionWork<String>()
		{
			public String execute(Transaction tx)
			{
				StatementResult result = tx.run(Query);
				while(result.hasNext())
				{
					FetchUrl fetchUrl = new FetchUrl();
					Record record= result.next();
//					System.out.println(result.next().toString());
					Gson gson = new Gson();
					JsonElement jsonElement = gson.toJsonTree(record.asMap());
					fetchUrl = gson.fromJson(jsonElement, FetchUrl.class);
					fetchList.add(fetchUrl);

					//fetchUrl = gson.fromJson(x, type);


					//					fetchUrl.setCodeCount(result.);
					//					System.out.println(fetchUrl);
				}
				return "Working";
			}
		});

		fetchList.sort(Comparator.comparing(FetchUrl::getConfidenceScore, (s1, s2) -> {
			return s2.compareTo(s1);
		}));
		for(int i=0; i<fetchList.size();i++){
//			System.out.println(fetchList.get(i).getConfidenceScore());
		}
		System.out.println(Greeting);
		return fetchList;

	}
//	public static void main(String...args){
//		UserInput userInput = new UserInput();
//		userInput.setConcept("Ajax");
//		userInput.setIntent("Advance");
//		FetchService test = new FetchService();
//		test.fetchedUrl("Match(n:url)-[x:"+userInput.getIntent()+"]->(c:concept{name:\""+userInput.getConcept()+"\""+"})return n.imgCount as imgCount,n.videoCount as videoCount,n.codeCount as codeCount,n.url as url,n.counterIndicator as counterIndicator, x.confidenceScore as confidenceScore");
//
//	}

}
