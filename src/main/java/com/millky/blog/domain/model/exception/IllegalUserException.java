package com.millky.blog.domain.model.exception;

public class IllegalUserException extends RuntimeException {

	private static final long serialVersionUID = 5372850228246790558L;

	public IllegalUserException() {
		super();
	}

	public IllegalUserException(String message) {
		super(message);
	}

	public IllegalUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalUserException(Throwable cause) {
		super(cause);
	}
}
