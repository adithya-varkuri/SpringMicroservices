package com.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication(scanBasePackages = { "com.apigateway.security"})
/*
 * Discovery service concept in spring cloud is implemented in different ways
 * like Eureka, consul, zookeeper etc. If you are using Eureka by Netflix
 * then @EnableEurekaClient is specifically for that. But if you are using any
 * other service discovery including Eureka you can use @EnableDiscoveryClient.
 */
@EnableEurekaClient
/*
 * Zuul Server is a gateway application that handles all the requests and does
 * the dynamic routing of microservice applications. The Zuul Server is also
 * known as Edge Server.
 * 
 * For Example, /api/user is mapped to the user service and /api/products is
 * mapped to the product service and Zuul Server dynamically routes the requests
 * to the respective backend application
 */
/*
 * The @EnableZuulProxy annotation is used to make your Spring Boot application
 * act as a Zuul Proxy server.
 */
@EnableZuulProxy
public class RewardsZuulApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsZuulApiGatewayApplication.class, args);
	}
	@Bean
	public CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("OPTIONS");
	    config.addAllowedMethod("HEAD");
	    config.addAllowedMethod("GET");
	    config.addAllowedMethod("PUT");
	    config.addAllowedMethod("POST");
	    config.addAllowedMethod("DELETE");
	    config.addAllowedMethod("PATCH");
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}

}
