package com.authentication.exception;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.authentication.serviceimpl.UserServiceImpl;

@ControllerAdvice
/*
 * @ControllerAdvice is an annotation provided by Spring allowing you to write
 * global code that can be applied to a wide range of controllers — varying from
 * all controllers to a chosen package or even a specific annotation
 */
//https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
	/*
	 * you can handle one and multiple messages by mentioning in brackets and change
	 * variable to exception instead of nullpointer or userCustomException
	 */
	@ExceptionHandler(value = { UserException.class })
	public ResponseEntity<Object> handleUserException(UserException ex, WebRequest req) {
		String errmsglocal = ex.getLocalizedMessage();
		log.info(errmsglocal);
		if (errmsglocal == null)
			errmsglocal = ex.toString();
		Errormessage errmsg = new Errormessage(new Date(), errmsglocal);
		return new ResponseEntity<>(errmsg, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
