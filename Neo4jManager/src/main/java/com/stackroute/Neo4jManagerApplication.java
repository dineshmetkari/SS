package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class Neo4jManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neo4jManagerApplication.class, args);
	}
}
