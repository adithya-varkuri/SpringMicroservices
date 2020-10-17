package com.authentication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.authentication.service.UserService;
import com.authentication.util.ConfigProperties;

@Configuration
/*
 * Annotating a class with the @Configuration indicates that the class can be
 * used by the Spring IoC container as a source of bean definitions. The @Bean
 * annotation tells Spring that a method annotated with @Bean will return an
 * object that should be registered as a bean in the Spring application contex
 */
/*
 * @EnableWebSecurity is used for spring security java configuration. Add this
 * annotation with @configuration on top of your security java class that
 * extends WebSecurityConfigurerAdapter.
 * 
 * Override the configure(WebSecurity web) & configure(HttpSecurity http). This
 * is the replacement of xml based configurations like and . This way you can
 * limit requested urls coming from specific urls also enable form based log in.
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment env;

	@Autowired
	UserService userDetailsService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	ConfigProperties config;

	/*
	 * public WebSecurity(Environment env) { this.env= env; }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/api/**").permitAll().antMatchers("/**")
				.hasIpAddress(env.getProperty("gateway.ip"))
				.and().addFilterBefore(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	}

	public AuthenticateFilter getAuthenticationFilter() throws Exception {
		AuthenticateFilter authenticateFilter = new AuthenticateFilter(userDetailsService, authenticationManager(),
				config);
		// authenticateFilter.setFilterProcessesUrl("api/auth/login");
		/*
		 * as in AuthenticateFilter we are using getauthenticationfilter so we are
		 * setting this values here other wise it will return null
		 */
		return authenticateFilter;
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
