package com.authentication.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.authentication.exception.UserException;
import com.authentication.requests.LoginRequest;
import com.authentication.service.UserService;
import com.authentication.serviceimpl.UserDetailsImpl;
import com.authentication.util.ConfigProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*UsernamePasswordAuthenticationFilter filter is only used for url /login*/
public class AuthenticateFilter extends UsernamePasswordAuthenticationFilter {

	UserService userDetailsService;

	ConfigProperties configProp;

	public AuthenticateFilter(UserService userDetailsService, AuthenticationManager authManger,
			ConfigProperties configProp) {
		this.userDetailsService = userDetailsService;
		super.setAuthenticationManager(authManger);
		this.configProp = configProp;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			LoginRequest cred = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(cred.getUserName(), cred.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		/**UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
		userDetails.getUsername();
		userDetailsService.loadUserByUsername(userDetails.getUsername());
		String jwtToken = Jwts.builder().setSubject((userDetails.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date(
						(new Date()).getTime() + Long.valueOf(configProp.getConfigValue("app.jwtExpirationMs"))))
				.signWith(SignatureAlgorithm.HS512, configProp.getConfigValue("app.jwtSecret")).compact();
		response.addHeader("token", jwtToken);
		response.addHeader("userId", userDetails.getUsername());**/

	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
		
	}
}
