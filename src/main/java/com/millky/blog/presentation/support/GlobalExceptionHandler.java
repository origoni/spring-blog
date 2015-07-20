package com.millky.blog.presentation.support;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.millky.blog.domain.model.exception.IllegalUserException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ IllegalArgumentException.class })
	void handleIllegalArgumentException(HttpServletResponse response) throws IOException {
		log.debug("IllegalArgumentException");
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler({ IllegalUserException.class })
	void handleIllegalUserException(HttpServletResponse response) throws IOException {
		log.debug("IllegalUserException");
		response.sendError(HttpStatus.FORBIDDEN.value());
	}
}
