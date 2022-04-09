/**
 * A Donut Hole object with specific flavors
 * @author Rory Xu, Hassan Alfareed
 */
package com.example.project4;

public class DonutHole extends MenuItem {
	private String flavor;
	private int quantity;

	public DonutHole(String flavor, int quantity) {
		this.flavor = flavor;
		this.quantity = quantity;
	}

	@Override
	public double itemPrice() {
		return 0.39 * quantity;
	}
}
