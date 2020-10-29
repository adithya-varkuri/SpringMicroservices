package com.usermaintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = { "com.usermaintenance.controller","com.usermaintenance.entities","com.usermaintenance.repositories",
		"com.usermaintenance.service","com.usermaintenance.serviceimpl"})
@EnableDiscoveryClient
public class RewardsUserMaintenanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsUserMaintenanceApplication.class, args);
	}

}
