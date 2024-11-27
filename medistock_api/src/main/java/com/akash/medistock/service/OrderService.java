package com.akash.medistock.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.medistock.pojo.Cart;
import com.akash.medistock.pojo.Medicine;
import com.akash.medistock.pojo.Order;
import com.akash.medistock.pojo.User;
import com.akash.medistock.repository.CartRepository;
import com.akash.medistock.repository.MedicineRepository;
import com.akash.medistock.repository.OrderRepository;
import com.akash.medistock.repository.UserRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartRepository cartRepository;

	public Order addOrder(long userId, long medicineId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		Optional<Medicine> optionalMedicine = medicineRepository.findById(medicineId);
		if (optionalUser.isPresent() && optionalUser.get().getRole().equals("BUYER") && optionalMedicine.isPresent()) {
			Order order = new Order();
			order.setNumber(order.hashCode());
			order.setMedicine(optionalMedicine.get());
			order.setDate(new Date(System.currentTimeMillis()));
			order.setStatus("PLACED");
			Order addedOrder = orderRepository.save(order);
			User user = optionalUser.get();
			List<Order> orders = user.getOrders();
			orders.add(addedOrder);
			user.setOrders(orders);
			Cart cart = user.getCart();
			List<Medicine> medicines = cart.getMedicines();
			medicines.remove(optionalMedicine.get());
			cart.setMedicines(medicines);
			cartRepository.save(cart);
			userRepository.save(user);
			return order;
		} else {
			return null;
		}
	}

}
