package com.akash.medistock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akash.medistock.exception.NullReferenceException;
import com.akash.medistock.pojo.Order;
import com.akash.medistock.response.Response;
import com.akash.medistock.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/order")
	public Response<Order> addOrder(@RequestParam(name = "userId") long userId,
			@RequestParam(name = "medicineId") long medicineId) {
		Order addedOrder = orderService.addOrder(userId, medicineId);
		if (addedOrder != null) {
			Response<Order> response = new Response<>();
			response.setMessage("ORDER PLACED");
			response.setHttpStatus(HttpStatus.CREATED);
			response.setHttpStatusCode(HttpStatus.CREATED.value());
			response.setData(addedOrder);
			return response;
		} else {
			throw new NullReferenceException("INVALID USER ID OR MEDICINE ID");
		}
	}

}
