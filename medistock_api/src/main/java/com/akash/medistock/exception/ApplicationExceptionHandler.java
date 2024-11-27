package com.akash.medistock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.akash.medistock.response.Response;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public Response<String> userNotFoundExceptionHandler(UserNotFoundException e) {
		Response<String> response = new Response<>();
		response.setMessage(e.getMessage());
		response.setHttpStatus(HttpStatus.NOT_FOUND);
		response.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
		return response;
	}

	@ExceptionHandler(MedicineNotFoundException.class)
	public Response<String> medicineNotFoundExceptionHandler(MedicineNotFoundException e) {
		Response<String> response = new Response<>();
		response.setMessage(e.getMessage());
		response.setHttpStatus(HttpStatus.NOT_FOUND);
		response.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
		return response;
	}

	@ExceptionHandler(InvalidUserException.class)
	public Response<String> invalidUserExceptionHandler(InvalidUserException e) {
		Response<String> response = new Response<>();
		response.setMessage(e.getMessage());
		response.setHttpStatus(HttpStatus.UNAUTHORIZED);
		response.setHttpStatusCode(HttpStatus.UNAUTHORIZED.value());
		return response;
	}

	@ExceptionHandler(NullReferenceException.class)
	public Response<String> nullReferenceExceptionHandler(NullReferenceException e) {
		Response<String> response = new Response<>();
		response.setMessage(e.getMessage());
		response.setHttpStatus(HttpStatus.BAD_REQUEST);
		response.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
		return response;
	}

	@ExceptionHandler(DuplicateMedicineInCartException.class)
	public Response<String> duplicateMedicineInCartExceptionHandler(DuplicateMedicineInCartException e) {
		Response<String> response = new Response<>();
		response.setMessage(e.getMessage());
		response.setHttpStatus(HttpStatus.NOT_MODIFIED);
		response.setHttpStatusCode(HttpStatus.NOT_MODIFIED.value());
		return response;
	}
}
