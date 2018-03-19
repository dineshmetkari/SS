package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 
 * This is the main class of this SpringBootApplication.
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RestaurantApplication {
	/**
	 * 
	 * This is the main method.
	 */
	public static void main(final String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}
}
