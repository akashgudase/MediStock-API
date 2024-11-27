package com.akash.medistock.controller;

import java.util.List;

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

import com.akash.medistock.exception.InvalidUserException;
import com.akash.medistock.exception.MedicineNotFoundException;
import com.akash.medistock.exception.NullReferenceException;
import com.akash.medistock.pojo.Cart;
import com.akash.medistock.pojo.Medicine;
import com.akash.medistock.pojo.User;
import com.akash.medistock.response.Response;
import com.akash.medistock.service.CartService;
import com.akash.medistock.service.MedicineService;
import com.akash.medistock.service.UserService;

@RestController
public class MedicineController {

	@Autowired
	private MedicineService medicineService;

	@Autowired
	private UserService userService;

	@Autowired
	private CartService cartService;

	@PostMapping(path = "/medicine")
	public Response<Medicine> addMedicine(@RequestParam(name = "userId") long userId, @RequestBody Medicine medicine) {
		User user = userService.findUserById(userId);
		if (user != null && user.getRole().equals("SELLER")) {
			Medicine addedMedicine = medicineService.addMedicine(medicine);
			if (addedMedicine != null) {
				Cart cart = user.getCart();
				List<Medicine> medicines = cart.getMedicines();
				medicines.add(addedMedicine);
				cart.setMedicines(medicines);
				cartService.updateCart(cart);
				Response<Medicine> response = new Response<>();
				response.setMessage("MEDICINE ADDED");
				response.setHttpStatus(HttpStatus.CREATED);
				response.setHttpStatusCode(HttpStatus.CREATED.value());
				response.setData(addedMedicine);
				return response;
			} else {
				throw new NullReferenceException("MEDICINE IS NULL");
			}
		} else {
			throw new InvalidUserException("UNAUTHORIZED USER");
		}
	}

	@GetMapping(path = "/medicines")
	public Response<List<Medicine>> findAllMedicines() {
		List<Medicine> medicines = medicineService.findAlledicines();
		if (medicines != null) {
			Response<List<Medicine>> response = new Response<>();
			response.setMessage("MEDICINES FETCHED");
			response.setHttpStatus(HttpStatus.FOUND);
			response.setHttpStatusCode(HttpStatus.FOUND.value());
			response.setData(medicines);
			return response;
		} else {
			throw new MedicineNotFoundException("MEDICINES NOT AVAILABLE");
		}
	}

	@GetMapping(path = "/medicines/sort/price")
	public Response<List<Medicine>> sortMedicinesByPrice() {
		List<Medicine> medicines = medicineService.sortMedicinesByPrice();
		if (medicines != null) {
			Response<List<Medicine>> response = new Response<>();
			response.setMessage("MEDICINES FETCHED");
			response.setHttpStatus(HttpStatus.FOUND);
			response.setHttpStatusCode(HttpStatus.FOUND.value());
			response.setData(medicines);
			return response;
		} else {
			throw new MedicineNotFoundException("MEDICINES NOT AVAILABLE");
		}
	}

	@GetMapping(path = "/medicines/sort/rating")
	public Response<List<Medicine>> sortMedicinesByRating() {
		List<Medicine> medicines = medicineService.sortMedicinesByRating();
		if (medicines != null) {
			Response<List<Medicine>> response = new Response<>();
			response.setMessage("MEDICINES FETCHED");
			response.setHttpStatus(HttpStatus.FOUND);
			response.setHttpStatusCode(HttpStatus.FOUND.value());
			response.setData(medicines);
			return response;
		} else {
			throw new MedicineNotFoundException("MEDICINES NOT AVAILABLE");
		}
	}

	@PutMapping(path = "/medicine")
	public Response<Medicine> updateMedicine(@RequestParam(name = "userId") long userId,
			@RequestBody Medicine medicine) {
		User user = userService.findUserById(userId);
		if (user != null && user.getRole().equals("SELLER")) {
			Medicine updatedMedicine = medicineService.updateMedicine(medicine);
			if (updatedMedicine != null) {
				Response<Medicine> response = new Response<>();
				response.setMessage("MEDICINE UPDATED");
				response.setHttpStatus(HttpStatus.CREATED);
				response.setHttpStatusCode(HttpStatus.CREATED.value());
				response.setData(updatedMedicine);
				return response;
			} else {
				throw new MedicineNotFoundException("INVALID ID OF THE MEDICINE");
			}
		} else {
			throw new InvalidUserException("UNAUTHORIZED USER");
		}
	}

	@DeleteMapping(path = "/medicine")
	public Object deleteMedicine(@RequestParam(name = "userId") long userId, @RequestParam(name = "id") long id) {
		User user = userService.findUserById(userId);
		if (user != null && user.getRole().equals("SELLER")) {
			Medicine deletedMedicine = medicineService.deleteMedicine(id);
			if (deletedMedicine != null) {
				Response<Medicine> response = new Response<>();
				response.setMessage("MEDICINE DELETED");
				response.setHttpStatus(HttpStatus.OK);
				response.setHttpStatusCode(HttpStatus.OK.value());
				response.setData(deletedMedicine);
				return response;
			} else {
				throw new MedicineNotFoundException("INVALID ID OF THE MEDICINE");
			}
		} else {
			throw new InvalidUserException("UNAUTHORIZED USER");
		}
	}

	@GetMapping(path = "/medicines/{brand}")
	public Response<List<Medicine>> findMedicinesByBrand(@PathVariable String brand) {
		List<Medicine> medicines = medicineService.findMedicinesByBrand(brand);
		if (medicines != null) {
			Response<List<Medicine>> response = new Response<>();
			response.setMessage("MEDICINES FETCHED");
			response.setHttpStatus(HttpStatus.FOUND);
			response.setHttpStatusCode(HttpStatus.FOUND.value());
			response.setData(medicines);
			return response;
		} else {
			throw new MedicineNotFoundException("MEDICINES NOT AVAILABLE");
		}
	}

}
