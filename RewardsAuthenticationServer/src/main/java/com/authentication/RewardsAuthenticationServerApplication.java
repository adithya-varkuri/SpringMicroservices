package com.authentication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.authentication.controller.AuthController;

/*This is a Spring Boot main class. A Spring Boot REST application loads through this class. We can also see that this class is created with the 
 * annotation @SpringBootApplication . As per the Spring documentation, the annotation @SpringBootApplication is equivalent to using @Configuration,
 *  @EnableAutoConfiguration, and @ComponentScan, and these annotations are frequently used together. Most of the time, in Spring Boot development,
 *   the main class is always annotated with all three of these important annotations.*/

/*Spring @Configuration annotation is part of the spring core framework. Spring Configuration annotation indicates that the class has @Bean
 *  definition methods. So Spring container can process the class and generate Spring Beans to be used in the application*/

/*@EnableAutoConfiguration automatically configures the Spring application based on its included jar files, it sets up defaults or helper based 
 * on dependencies in pom.xml. Auto-configuration is usually applied based on the classpath and the defined beans. Therefore, we donot need to define 
 * any of the DataSource, EntityManagerFactory, TransactionManager etc and magically based on the classpath, Spring Boot automatically creates proper
 *  beans and registers them for us.*/

/*@ComponentScan provides scope for spring component scan, it simply goes though the provided base package and picks up dependencies required by
 *  @Bean or @Autowired etc, In a typical Spring application, @ComponentScan is used in a configuration classes, the ones annotated with @Configuration.
 *   Configuration classes contains methods annotated with @Bean. These @Bean annotated methods generate beans managed by Spring container. Those beans 
 *   will be auto-detected by @ComponentScan annotation. There are some annotations which make beans auto-detectable like @Repository , @Service,
 *    @Controller, @Configuration, @Component*/

/*So by default, all packages that falls under @SpringBootApplication declaration will be scanned.

Assuming my main class ExampleApplication that has @SpringBootApplication declaration is declared inside com.example.something, then all components that falls under com.example.something is scanned while com.example.applicant will not be scanned.

So, there are two ways to do it based on this question. Use

@SpringBootApplication(scanBasePackages={
"com.example.something", "com.example.application"})*/

/*So I use the second approach, by restructuring my packages and it worked ! Now my packages structure became like this.

src/
├── main/
│   └── java/
|       ├── com.example/
|       |   └── Application.java
|       ├── com.example.model/
|       |   └── User.java
|       ├── com.example.controller/
|       |   ├── IndexController.java
|       |   └── UsersController.java
|       └── com.example.service/
|           └── UserService.java
└── resources/
    └── application.properties*/
@SpringBootApplication(scanBasePackages = { "com.authentication.security", "com.authentication.controller",
		"com.authentication.service", "com.authentication.serviceimpl", "com.authentication.repositories",
		"com.authentication.config", "com.authentication.util", "com.authentication.entities",
		"com.authentication.models","com.authentication.exception" })
/*
 * @EnableDiscoveryClient annotation can work with any Discovery Client
 * implementations which implements in your project ( Eureka, Consul, Zookeeper
 * ) . You can also use @EnableEurekaClient annotation but it works only with
 * Eureka Discovery Client implementation
 */
//https://springbootdev.com/2018/01/15/microservices-service-registration-and-discover-in-netflix-eureka/
@EnableDiscoveryClient

public class RewardsAuthenticationServerApplication {
	private static final Logger LOGGER = LogManager.getLogger(AuthController.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(RewardsAuthenticationServerApplication.class, args);
		LOGGER.info("Starting application");

	}

	/* If we don’t specify, it will use plain text. */
	@Bean
	public PasswordEncoder passwordEncoder() {
		LOGGER.info("Initializing BCryptPasswordEncoder");
		return new BCryptPasswordEncoder();
	}
	
	/*
	 * @Bean public CorsFilter corsFilter() { final UrlBasedCorsConfigurationSource
	 * source = new UrlBasedCorsConfigurationSource(); final CorsConfiguration
	 * config = new CorsConfiguration(); config.setAllowCredentials(true);
	 * config.addAllowedOrigin("*"); config.addAllowedHeader("*");
	 * config.addAllowedMethod("OPTIONS"); config.addAllowedMethod("HEAD");
	 * config.addAllowedMethod("GET"); config.addAllowedMethod("PUT");
	 * config.addAllowedMethod("POST"); config.addAllowedMethod("DELETE");
	 * config.addAllowedMethod("PATCH"); source.registerCorsConfiguration("/**",
	 * config); return new CorsFilter(source); }
	 */

	


}
