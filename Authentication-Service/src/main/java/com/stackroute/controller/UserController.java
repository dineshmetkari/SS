package com.stackroute.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.domain.User;
import com.stackroute.service.UserService;
import com.stackroute.utils.CredentialsVerifier;
import com.stackroute.utils.TokenGenerator;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired 
	private CredentialsVerifier credentialsVerifier;
	
	@PostMapping(value = "/register")
	/**
	 * This addRestaurant method is calling another addRestaurant method from
	 * RestaurantService to add the restaurant object in the database
	 * 
	 * @param restaurant
	 * @return Restaurant
	 */
	public ResponseEntity<?> addUser(@RequestBody final User user) {
		System.out.println("In register end point");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userService.addUser(user);
		JSONObject json = new JSONObject();
		try {
			json.put("message","Successfully Registered");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
		

	}
	
	@PostMapping(value = "/login")
	/**
	 * This addRestaurant method is calling another addRestaurant method from
	 * RestaurantService to add the restaurant object in the database
	 * 
	 * @param restaurant
	 * @return Restaurant
	 */
	public ResponseEntity<String> loginUser(@RequestBody final User user) {
		final User fetchedUser = userService.searchByEmailId(user.getEmailId());
		String authResult = credentialsVerifier.verify(user, fetchedUser);
		System.out.println(authResult);
		return new ResponseEntity<String>(authResult, HttpStatus.OK);
	}
	
	@GetMapping(value = "/showAll")
	/**
	 * This addRestaurant method is calling another addRestaurant method from
	 * RestaurantService to add the restaurant object in the database
	 * 
	 * @param restaurant
	 * @return Restaurant
	 */
	public ResponseEntity<List<User>> getAllDomainExperts() {
		final List<User> fetchedUsers = userService.searchByRole("Domain Expert");
		return new ResponseEntity<List<User>>(fetchedUsers, HttpStatus.OK);
	}
	
	@GetMapping(value = "/delete/{emailId}")
	/**
	 * This addRestaurant method is calling another addRestaurant method from
	 * RestaurantService to add the restaurant object in the database
	 * 
	 * @param restaurant
	 * @return Restaurant
	 */
	public ResponseEntity<String> deleteDomainExpert(@PathVariable("emailId") final String emailId) {
		System.out.println("Delete DE" + emailId);
		final User fetchedUser = userService.searchByEmailId(emailId);
		final String responseMessage = userService.deleteDomainExpert(fetchedUser.getId());
		System.out.println("deleted"+ responseMessage);
		return new ResponseEntity<String>(responseMessage, HttpStatus.OK);
	}
}
