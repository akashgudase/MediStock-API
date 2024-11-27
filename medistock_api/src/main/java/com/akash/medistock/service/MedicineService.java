package com.akash.medistock.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.medistock.comparator.PriceComparator;
import com.akash.medistock.comparator.RatingComparator;
import com.akash.medistock.pojo.Medicine;
import com.akash.medistock.repository.MedicineRepository;

@Service
public class MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;

	public Medicine addMedicine(Medicine medicine) {
		if (medicine != null) {
			return medicineRepository.save(medicine);
		} else {
			return null;
		}
	}

	public Medicine findMedicineById(long id) {
		Optional<Medicine> optional = medicineRepository.findById(id);
		if (optional.isPresent()) {
			Medicine medicine = optional.get();
			return medicine;
		} else {
			return null;
		}
	}

	public List<Medicine> findAlledicines() {
		List<Medicine> medicines = medicineRepository.findAll();
		if (medicines.size() > 0) {
			return medicines;
		} else {
			return null;
		}
	}

	public List<Medicine> sortMedicinesByPrice() {
		List<Medicine> medicines = medicineRepository.findAll();
		if (medicines.size() > 0) {
			Collections.sort(medicines, new PriceComparator());
			return medicines;
		} else {
			return null;
		}
	}

	public List<Medicine> sortMedicinesByRating() {
		List<Medicine> medicines = medicineRepository.findAll();
		if (medicines.size() > 0) {
			Collections.sort(medicines, new RatingComparator());
			return medicines;
		} else {
			return null;
		}
	}

	public Medicine updateMedicine(Medicine medicine) {
		if (medicine != null) {
			Optional<Medicine> optional = medicineRepository.findById(medicine.getId());
			if (optional.isPresent()) {
				return medicineRepository.save(medicine);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public Medicine deleteMedicine(long id) {
		Optional<Medicine> optional = medicineRepository.findById(id);
		if (optional.isPresent()) {
			Medicine medicine = optional.get();
			medicineRepository.delete(medicine);
			return medicine;
		} else {
			return null;
		}
	}

	public List<Medicine> findMedicinesByBrand(String brand) {
		List<Medicine> medicines = medicineRepository.findMedicinesByBrand(brand);
		if (medicines.size() > 0) {
			return medicines;
		} else {
			return null;
		}
	}

}
