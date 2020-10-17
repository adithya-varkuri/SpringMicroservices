package com.eureka;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/*Eureka Server is an application that holds the information about all client-service applications. Every Micro service will register into
 *  the Eureka server and Eureka server knows all the client applications running on each port and IP address. Eureka Server is also known as
 *   Discovery Server.*/

@SpringBootApplication(scanBasePackages = { "com.eureka.security"})
/*
 * The @EnableEurekaServer annotation is used to make your Spring Boot
 * application acts as a Eureka Server
 */
@EnableEurekaServer
public class RewardsEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsEurekaServerApplication.class, args);
	}

}
