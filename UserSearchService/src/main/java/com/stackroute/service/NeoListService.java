package com.stackroute.service;

import java.util.ArrayList;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.stackroute.model.Neo4jModel;
@Service
public class NeoListService {
	@Autowired
	private Neo4jModel neoModel;
	private  Driver driver;

	@Value("${uri}")
	String uri;
	@Value("${username}")
	String user;
	@Value("${password}")
	String password;

	public Neo4jModel neo4jList(String Query1,String Query2){
		ArrayList<String> conceptList = new ArrayList<>();
		ArrayList<String> domainList = new ArrayList<>();
		driver = GraphDatabase.driver(uri,AuthTokens.basic(user, password));
		Session session1 = driver.session();
		String Message1 = session1.writeTransaction(new TransactionWork<String>()
		{
			public String execute(Transaction tx)
			{
				StatementResult result = tx.run(Query1);
				while(result.hasNext())
				{

					Record record = result.next();
					String concept = record.get("concept").asString();
					conceptList.add(concept);
				}
				neoModel.setConceptList(conceptList);
				return "Neo4j ConceptList Working";
			}
		});
		Session session2 = driver.session();
		String Message2 = session2.writeTransaction(new TransactionWork<String>()
		{
			public String execute(Transaction tx)
			{
				StatementResult result = tx.run(Query2);
				while(result.hasNext())
				{

					Record record= result.next();
					String domain = record.get("domain").asString();
					domainList.add(domain);
				}
				neoModel.setDomainList(domainList);
				return "Neo4jDomainList Working";
			}
		});

		return neoModel;

	}

}
