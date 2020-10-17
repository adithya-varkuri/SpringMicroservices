package com.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
/*
 * The @EnableConfigServer annotation makes your Spring Boot application act as
 * a Configuration Server
 */
public class RewardsConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsConfigServerApplication.class, args);
	}

}
