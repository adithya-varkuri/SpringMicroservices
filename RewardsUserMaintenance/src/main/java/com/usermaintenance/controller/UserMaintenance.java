package com.usermaintenance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.usermaintenance.entities.User;
import com.usermaintenance.service.UserService;

@RestController
@RequestMapping("/UserMaintance")
public class UserMaintenance {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/Users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

}
