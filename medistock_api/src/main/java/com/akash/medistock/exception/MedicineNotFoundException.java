package com.akash.medistock.exception;

import lombok.Getter;

@Getter
public class MedicineNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public MedicineNotFoundException(String message) {
		super();
		this.message = message;
	}

}
