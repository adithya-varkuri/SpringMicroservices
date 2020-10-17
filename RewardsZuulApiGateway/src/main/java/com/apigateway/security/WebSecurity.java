package com.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private Environment  env ;
	
	@Autowired
	public WebSecurity(Environment env) {
		this.env= env;
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/rewardsauthenticationserver/login").permitAll()
		.antMatchers(HttpMethod.POST,"/rewardsauthenticationserver/api/auth/signup").permitAll()
		.antMatchers("/rewardsauthenticationserver/actuator/*").permitAll()
		.antMatchers("/actuator/*").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new AutherizationFilter(authenticationManager(), env));
		
				
	}
	
		
}

