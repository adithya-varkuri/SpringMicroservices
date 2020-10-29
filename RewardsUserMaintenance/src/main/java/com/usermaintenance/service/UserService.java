package com.usermaintenance.service;

import java.util.List;

import com.usermaintenance.entities.Role;
import com.usermaintenance.exception.UserException;
import com.usermaintenance.response.RoleDetails;
import com.usermaintenance.response.UserDetails;

public interface UserService {

	List<UserDetails> getAllUsers();
	
	 UserDetails getLogedInUser(String username) throws UserException;
	 
	 List<RoleDetails> getAllRoles();
	 
	 public void updateRoles(UserDetails userdetails);

}
