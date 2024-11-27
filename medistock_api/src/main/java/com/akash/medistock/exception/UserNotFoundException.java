package com.akash.medistock.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public UserNotFoundException(String message) {
		super(message);
		this.message = message;
	}

}
