package com.akash.medistock.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.medistock.pojo.Cart;
import com.akash.medistock.pojo.Medicine;
import com.akash.medistock.pojo.Order;
import com.akash.medistock.pojo.User;
import com.akash.medistock.repository.CartRepository;
import com.akash.medistock.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartRepository cartRepository;

	public User addUser(User user) {
		if (user != null) {
			Cart cart = new Cart();
			cart.setMedicines(new ArrayList<Medicine>());
			Cart addedCart = cartRepository.save(cart);
			user.setCart(addedCart);
			if (user.getRole().equals("BUYER")) {
				user.setOrders(new ArrayList<Order>());
			}
			return userRepository.save(user);
		} else {
			return null;
		}
	}

	public User findUserById(long id) {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			User user = optional.get();
			return user;
		} else {
			return null;
		}
	}

	public User findUserByEmailAndPassword(String email, String password) {
		return userRepository.findUserByEmailAndPassword(email, password);
	}

	public User findUserByMobileAndPassword(long mobile, String password) {
		return userRepository.findUserByMobileAndPassword(mobile, password);
	}

	public User updateUser(User user) {
		if (user != null) {
			Optional<User> optional = userRepository.findById(user.getId());
			if (optional.isPresent()) {
				user.setCart(optional.get().getCart());
				return userRepository.save(user);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public User deleteUser(long id) {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			User user = optional.get();
			userRepository.delete(user);
			return user;
		} else {
			return null;
		}
	}

}
