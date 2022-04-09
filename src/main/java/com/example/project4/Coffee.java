package com.example.project4;

import java.util.ArrayList;
import java.util.List;

public class Coffee extends MenuItem implements Customizable{

	private List<AddIns> addIns = new ArrayList<>();
	private int basePrice;

	public Coffee(int basePrice) {
		this.basePrice = basePrice;
	}

	@Override
	public boolean add(Object obj) {
		return false;
	}

	@Override
	public boolean remove(Object obj) {
		return false;
	}

	@Override
	public double itemPrice() {
		int addInsPrice = addIns.size();
		return basePrice + 0.30 * addInsPrice;
	}
}
