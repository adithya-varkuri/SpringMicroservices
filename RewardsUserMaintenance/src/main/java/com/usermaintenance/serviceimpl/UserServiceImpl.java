package com.usermaintenance.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermaintenance.entities.Role;
import com.usermaintenance.entities.User;
import com.usermaintenance.exception.UserException;
import com.usermaintenance.repositories.RoleRepository;
import com.usermaintenance.repositories.UserRepository;
import com.usermaintenance.response.RoleDetails;
import com.usermaintenance.response.UserDetails;
import com.usermaintenance.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	

	@Override
	public List<UserDetails> getAllUsers() {
		List<UserDetails> users = new ArrayList<UserDetails>();
		
		userRepository.findAll().forEach( user-> {
			UserDetails userDetails = new UserDetails();
			userDetails.setUsername(user.getUsername());
			userDetails.setEmail(user.getEmail());
			userDetails.setActive(user.getActive());
			userDetails.setId(user.getId());
			userDetails.setRoles(user.getRoles());
			users.add(userDetails);
		});
		return users;
		
	}
	
	@Override
	@Transactional
	public UserDetails getLogedInUser(String username) throws UserException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserException("User Not Found with username: " + username));
		UserDetails userDetails = new UserDetails();
		userDetails.setUsername(user.getUsername());
		userDetails.setEmail(user.getEmail());
		userDetails.setActive(user.getActive());
		userDetails.setId(user.getId());
		userDetails.setRoles(user.getRoles());
		return userDetails;
	}

	@Override
	public List<RoleDetails> getAllRoles() {
		List<RoleDetails> roles = new ArrayList<RoleDetails>();
		 roleRepository.findAll().forEach(role ->{
			 RoleDetails roleDetails = new RoleDetails();
			 roleDetails.setId(role.getId());
			 roleDetails.setRole(role.getRole());
			 roles.add(roleDetails);
		 });
		return roles;
	}
	
	public void updateRoles(UserDetails userdetails) {
		
		User user = userRepository.findByUsername(userdetails.getUsername())
				.orElseThrow(() -> new UserException("User Not Found with username: " + userdetails.getUsername()));
		
		user.setRoles(userdetails.getRoles());
		userRepository.save(user);
		
	}

}
