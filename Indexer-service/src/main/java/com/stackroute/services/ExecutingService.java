package com.stackroute.services;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

import com.stackroute.domain.Confidence;

public class ExecutingService implements AutoCloseable {
	private final Driver driver;

	public ExecutingService(String uri, String username, String password) {

		driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
	}

	@Override
	public void close() throws Exception {
		driver.close();
	}

	private void createConfidenceNode(final Confidence confidence) {
		try (Session session = driver.session()) {
			String greeting = session.writeTransaction(new TransactionWork<String>() {
				@Override
				public String execute(Transaction tx) {
			/*		StatementResult st = tx.run("MATCH (c:Concept{name:\"" + confidence.getConceptName()
							+ "\"})-[:SubConceptOf*]->(v) \n" + "RETURN count(c) > 0 as c");
					if (st.single().get(0).isFalse())
						tx.run("MATCH(domain:Domain {name:\"" + confidence.getDomainName()
								+ "\"}) MERGE (i1:concept{name:\"" + confidence.getConceptName()
								+ "\"})-[:SubConceptOf]->(domain)");*/
					tx.run("MATCH(concept:concept{name:\"" + confidence.getConceptName()
							+ "\"})-[:SubConceptOf*]->(:Domain{name:\"" + confidence.getDomainName()
							+ "\"}) MERGE(i1:url{url:\"" + confidence.getUrl() + "\",imgCount:"
							+ confidence.getImageCount() + ",videoCount:" + confidence.getVideoCount() + ",codeCount:"
							+ confidence.getCodeCount() + ",titleUrl:\""+confidence.getTitleUrl()+"\",metaUrl:\""+confidence.getMetaUrl()+"\",counterIndicator:" + confidence.getCounterIndicator()
							+ "})-[:" + confidence.getIntent() + "{" + "confidenceScore:"
							+ confidence.getConfidenceScore() + "}" + "]->(concept)");
					// tx.run("abc");

					return "Inserted: " + confidence.toString();
				}
			});
			System.out.println(greeting);
		}
	}

	public static void executeQuery(Confidence confidence, String uri, String username, String password) {
		try (ExecutingService greeter = new ExecutingService(uri, username, password)) {

			greeter.createConfidenceNode(confidence);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
