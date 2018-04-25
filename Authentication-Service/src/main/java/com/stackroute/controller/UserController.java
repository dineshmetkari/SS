package com.stackroute.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
//	 @Autowired
//	 private AuthenticationManager authenticationManager;
//	
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
		
//		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
//				new UsernamePasswordAuthenticationToken(fetchedUser,user);
//		authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//		
//		 if (usernamePasswordAuthenticationToken.isAuthenticated()) {
//				return new ResponseEntity<String>("success", HttpStatus.OK);
//
//		 }
//		return new ResponseEntity<String>("failure", HttpStatus.OK);

	}
}
