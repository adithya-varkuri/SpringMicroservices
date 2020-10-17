package com.usermaintenance.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermaintenance.entities.User;
import com.usermaintenance.repositories.UserRepository;
import com.usermaintenance.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
		
	}

}
