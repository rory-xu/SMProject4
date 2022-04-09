/**
 * A Yeast Donut object with specific flavors
 * @author Rory Xu, Hassan Alfareed
 */
package com.example.project4;

public class YeastDonut extends MenuItem {
	private String flavor;
	private int quantity;

	public YeastDonut(String flavor, int quantity) {
		this.flavor = flavor;
		this.quantity = quantity;
	}

	@Override
	public double itemPrice() {
		return 1.59 * quantity;
	}
}
