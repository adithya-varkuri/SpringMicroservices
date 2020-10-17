package com.authentication.serviceimpl;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authentication.entities.User;
import com.authentication.exception.UserException;
import com.authentication.repositories.UserRepository;
import com.authentication.requests.SignupRequest;
import com.authentication.responses.MessageResponse;
import com.authentication.service.UserService;
import com.authentication.util.ConfigProperties;

/*
– UserDetailsService interface has a method to load User by username and returns a UserDetails object that Spring Security can use for authentication and validation.

– UserDetails contains necessary information (such as: username, password, authorities) to build an Authentication object.*/
@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	ConfigProperties configProp;

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
	
	public MessageResponse registerUser(SignupRequest signUpRequest) {
		log.info("registring user : " + signUpRequest);
		if (userRepository.existsByUsername(signUpRequest.getUserName())) {
			throw new UserException(configProp.getConfigValue("auth.user.name.taken"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new UserException(configProp.getConfigValue("auth.user.email.taken"));
		}

		User user = new User(signUpRequest.getUserName(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		userRepository.save(user);
		return new MessageResponse( configProp.getConfigValue("auth.user.register.success"));
	}

}
