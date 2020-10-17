package com.authentication.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.authentication.entities.User;
import com.authentication.requests.SignupRequest;
import com.authentication.responses.MessageResponse;

public interface UserService extends UserDetailsService {

	User findUserByEmail(String email);

	public MessageResponse registerUser(SignupRequest signUpRequest);

}
