package com.akash.medistock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akash.medistock.exception.NullReferenceException;
import com.akash.medistock.exception.UserNotFoundException;
import com.akash.medistock.pojo.User;
import com.akash.medistock.response.Response;
import com.akash.medistock.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/user")
	public Response<User> addUser(@RequestBody User user) {
		User addedUser = userService.addUser(user);
		if (addedUser != null) {
			Response<User> response = new Response<>();
			response.setMessage("USER ADDED");
			response.setHttpStatus(HttpStatus.CREATED);
			response.setHttpStatusCode(HttpStatus.CREATED.value());
			response.setData(addedUser);
			return response;
		} else {
			throw new NullReferenceException("USER IS NULL");
		}
	}

	@GetMapping(path = "/user")
	public Response<User> findUserByEmailAndPassword(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) {
		User user = userService.findUserByEmailAndPassword(email, password);
		if (user != null) {
			Response<User> response = new Response<>();
			response.setMessage("USER FETCHED");
			response.setHttpStatus(HttpStatus.FOUND);
			response.setHttpStatusCode(HttpStatus.FOUND.value());
			response.setData(user);
			return response;
		} else {
			throw new UserNotFoundException("INVALID EMAIL OR PASSWORD");
		}
	}

	@GetMapping(path = "/user/{mobile}/{password}")
	public Response<User> findUserByMobileAndPassword(@PathVariable long mobile, @PathVariable String password) {
		User user = userService.findUserByMobileAndPassword(mobile, password);
		if (user != null) {
			Response<User> response = new Response<>();
			response.setMessage("USER FETCHED");
			response.setHttpStatus(HttpStatus.FOUND);
			response.setHttpStatusCode(HttpStatus.FOUND.value());
			response.setData(user);
			return response;
		} else {
			throw new UserNotFoundException("INVALID MOBILE NUMBER OR PASSWORD");
		}
	}

	@PutMapping(path = "/user")
	public Response<User> updateUser(@RequestBody User user) {
		User updatedUser = userService.updateUser(user);
		if (updatedUser != null) {
			Response<User> response = new Response<>();
			response.setMessage("USER UPDATED");
			response.setHttpStatus(HttpStatus.CREATED);
			response.setHttpStatusCode(HttpStatus.CREATED.value());
			response.setData(updatedUser);
			return response;
		} else {
			throw new UserNotFoundException("INVALID ID OF THE USER");
		}
	}

	@DeleteMapping(path = "/user")
	public Response<User> deleteUser(@RequestParam(name = "id") long id) {
		User deletedUser = userService.deleteUser(id);
		if (deletedUser != null) {
			Response<User> response = new Response<>();
			response.setMessage("USER DELETED");
			response.setHttpStatus(HttpStatus.OK);
			response.setHttpStatusCode(HttpStatus.OK.value());
			response.setData(deletedUser);
			return response;
		} else {
			throw new UserNotFoundException("INVALID ID OF THE USER");
		}
	}

}
