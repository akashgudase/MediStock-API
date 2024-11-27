package com.akash.medistock.exception;

import lombok.Getter;

@Getter
public class DuplicateMedicineInCartException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public DuplicateMedicineInCartException(String message) {
		super();
		this.message = message;
	}

}
