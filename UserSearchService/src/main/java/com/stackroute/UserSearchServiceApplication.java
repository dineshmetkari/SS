package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserSearchServiceApplication.class, args);
	}
}
