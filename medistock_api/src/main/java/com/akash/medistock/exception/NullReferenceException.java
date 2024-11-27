package com.akash.medistock.exception;

import lombok.Getter;

@Getter
public class NullReferenceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public NullReferenceException(String message) {
		super();
		this.message = message;
	}

}
