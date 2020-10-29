package com.usermaintenance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.usermaintenance.response.RoleDetails;
import com.usermaintenance.response.UserDetails;
import com.usermaintenance.service.UserService;

@RestController
@RequestMapping("/UserMaintance")
public class UserMaintenance {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/Users")
	public List<UserDetails> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/User/{userName}")
	public UserDetails getLogedinUser(@PathVariable("userName") String username) {
		return userService.getLogedInUser(username);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/Roles")
	public List<RoleDetails> getAllRoles() {
		return userService.getAllRoles();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/updateUserRoles")
	public void updateUserRoles(@RequestBody UserDetails userdetails) {
		 userService.updateRoles(userdetails);
	}

}
