package com.usermaintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.usermaintenance.controller","com.usermaintenance.entities","com.usermaintenance.repositories",
		"com.usermaintenance.service","com.usermaintenance.serviceimpl"})
public class RewardsUserMaintenanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsUserMaintenanceApplication.class, args);
	}

}
