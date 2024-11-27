package com.akash.medistock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akash.medistock.exception.DuplicateMedicineInCartException;
import com.akash.medistock.exception.InvalidUserException;
import com.akash.medistock.exception.MedicineNotFoundException;
import com.akash.medistock.pojo.Cart;
import com.akash.medistock.pojo.Medicine;
import com.akash.medistock.pojo.User;
import com.akash.medistock.response.Response;
import com.akash.medistock.service.CartService;
import com.akash.medistock.service.MedicineService;
import com.akash.medistock.service.UserService;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private UserService userService;

	@Autowired
	private MedicineService medicineService;

	@PutMapping(path = "/cart-a")
	public Response<User> addMedicineToCart(@RequestParam(name = "userId") long userId,
			@RequestParam(name = "medicineId") long medicineId) {
		User user = userService.findUserById(userId);
		if (user != null && user.getRole().equals("BUYER")) {
			Medicine medicine = medicineService.findMedicineById(medicineId);
			if (medicine != null) {
				Cart cart = user.getCart();
				List<Medicine> medicines = cart.getMedicines();
				if (!medicines.contains(medicine)) {
					medicines.add(medicine);
					cart.setMedicines(medicines);
					Cart updatedCart = cartService.updateCart(cart);
					user.setCart(updatedCart);
					User updatedUser = userService.updateUser(user);
					Response<User> response = new Response<>();
					response.setMessage("MEDICINE ADDED TO CART");
					response.setHttpStatus(HttpStatus.CREATED);
					response.setHttpStatusCode(HttpStatus.CREATED.value());
					response.setData(updatedUser);
					return response;
				} else {
					throw new DuplicateMedicineInCartException("MEDICINE ALREADY ADDED TO CART");
				}
			} else {
				throw new MedicineNotFoundException("INVALID ID OF THE MEDICINE");
			}
		} else {
			throw new InvalidUserException("UNAUTHORIZED USER");
		}
	}

	@PutMapping(path = "/cart-d")
	public Response<User> deleteMedicineFromCart(@RequestParam(name = "userId") long userId,
			@RequestParam(name = "medicineId") long medicineId) {
		User user = userService.findUserById(userId);
		if (user != null && user.getRole().equals("BUYER")) {
			Medicine medicine = medicineService.findMedicineById(medicineId);
			if (medicine != null) {
				Cart cart = user.getCart();
				List<Medicine> medicines = cart.getMedicines();
				medicines.remove(medicine);
				cart.setMedicines(medicines);
				Cart updatedCart = cartService.updateCart(cart);
				user.setCart(updatedCart);
				User updatedUser = userService.updateUser(user);
				Response<User> response = new Response<>();
				response.setMessage("MEDICINE DELETED FROM CART");
				response.setHttpStatus(HttpStatus.OK);
				response.setHttpStatusCode(HttpStatus.OK.value());
				response.setData(updatedUser);
				return response;
			} else {
				throw new MedicineNotFoundException("INVALID ID OF THE MEDICINE");
			}
		} else {
			throw new InvalidUserException("UNAUTHORIZED USER");
		}
	}

}
