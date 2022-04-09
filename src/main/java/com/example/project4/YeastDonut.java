/**
 * A Yeast Donut object with specific flavors
 * @author Rory Xu, Hassan Alfareed
 */
package com.example.project4;

public class YeastDonut extends Donut {

	public YeastDonut(String flavor, int quantity) {
		super(flavor, quantity);

	}

	@Override
	public double itemPrice() {
		return 1.59 * quantity;
	}

	@Override
	public String toString() {
		return flavor + "(" + quantity + ")";
	}
}
