package com.akash.medistock.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {

	private String message;
	private HttpStatus httpStatus;
	private int httpStatusCode;
	private T data;

}
