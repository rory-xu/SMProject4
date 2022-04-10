package com.example.project4;

import java.util.ArrayList;
import java.util.List;

/**
 * A coffee object that can have many addons
 * @author Rory Xu, Hassan Alfareed
 */
public class Coffee extends MenuItem implements Customizable {

	private List<String> addIns = new ArrayList<>();
	private double basePrice;
	private int quantity = 1;

	public Coffee(double basePrice) {
		this.basePrice = basePrice;
	}

	public void changeBasePrice(double newPrice) {
		basePrice = newPrice;
	}

	public void changeQuantity(int newQuantity) {
		quantity = newQuantity;
	}

	public List<String> getAddIns() {
		return this.addIns;
	}

	@Override
	public boolean add(Object obj) {
		if (obj instanceof String) {
			addIns.add((String) obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object obj) {
		if (obj instanceof String && addIns.contains(obj)) {
			addIns.remove(obj);
			return true;
		}
		return false;
	}

	@Override
	public double itemPrice() {
		int addInsPrice = addIns.size();
		return (basePrice + 0.30 * addInsPrice) * quantity;
	}

	@Override
	public String toString() {
		String display = null;
		switch (Double.toString(basePrice)) {
			case ("1.69"):
				display = "Coffee(" + quantity + ") Short " + addIns.toString();
				break;
			case ("2.09"):
				display = "Coffee(" + quantity + ") Tall " + addIns.toString();
				break;
			case ("2.49"):
				display = "Coffee(" + quantity + ") Grande " + addIns.toString();
				break;
			case ("2.89"):
				display = "Coffee(" + quantity + ") Venti " + addIns.toString();
				break;
		}
		return display;
	}
}
