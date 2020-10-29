package com.authentication.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.requests.LoginRequest;
import com.authentication.requests.SignupRequest;
import com.authentication.responses.JwtResponse;
import com.authentication.responses.MessageResponse;
import com.authentication.service.UserService;
import com.authentication.serviceimpl.UserDetailsImpl;
import com.authentication.util.JwtUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//use model mapper

/*Controller receives and handles request after it was filtered by OncePerRequestFilter.*/

@CrossOrigin(origins = "*", maxAge = 3600)
/*
 * Spring RestController annotation is a convenience annotation that is itself
 * annotated with @Controller and @ResponseBody. This annotation is applied to a
 * class to mark it as a request handler.
 */
/*
 * Spring RestController annotation is used to create RESTful web services using
 * Spring MVC. Spring RestController takes care of mapping request data to the
 * defined request handler method. Once response body is generated from the
 * handler method, it converts it to JSON or XML response
 */
/*
 * The job of @Controller is to create a Map of the model object and find a view
 * but @RestController simply returns the object and object data is directly
 * written into HTTP response as JSON or XML.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private static final Logger LOGGER = LogManager.getLogger(AuthController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;


	@Autowired
	PasswordEncoder encoder;

	@PostMapping(path = "/signin", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	// @Valid is used to validate the values inthe body
	//@Valid is used to validate the values inthe body
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		/*
		 *  UsernamePasswordAuthenticationToken gets {username, password} from login Request, AuthenticationManager will use
		 * it to authenticate a login account.
		 */
		/*
		 * AuthenticationManager has a DaoAuthenticationProvider (with help of
		 * UserDetailsService & PasswordEncoder) to validate
		 * UsernamePasswordAuthenticationToken object. If successful,
		 * AuthenticationManager returns a fully populated Authentication object
		 * (including granted authorities).
		 */
		/*
		 * If the authentication process is successful, we can get User’s information
		 * such as username, password, authorities from an Authentication object.
		 */
		/*
		 * If we want to get more data (id, email…), we can create an implementation of
		 * this UserDetails interface. like UserDetailsImpl
		 */
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}


	@PostMapping(path = "/signup", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		LOGGER.info("Starting register user");

		MessageResponse msgResponse = userService.registerUser(signUpRequest);
		LOGGER.info("End of registerUser");
		return ResponseEntity.status(HttpStatus.OK).body(msgResponse);
	}

}

/* https://bezkoder.com/spring-boot-jwt-authentication/ */