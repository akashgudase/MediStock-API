package com.akash.medistock.comparator;

import java.util.Comparator;

import com.akash.medistock.pojo.Medicine;

public class RatingComparator implements Comparator<Medicine> {

	@Override
	public int compare(Medicine o1, Medicine o2) {
		if (o1.getRating() == o2.getRating()) {
			return 0;
		} else if (o1.getRating() > o2.getRating()) {
			return 1;
		} else {
			return -1;
		}
	}

}
