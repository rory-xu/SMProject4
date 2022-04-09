/**
 * A Donut Hole object with specific flavors
 * @author Rory Xu, Hassan Alfareed
 */
package com.example.project4;

public class DonutHole extends Donut {

	public DonutHole(String flavor, int quantity) {
		super(flavor, quantity);
	}

	@Override
	public double itemPrice() {
		return 0.39 * quantity;
	}

	@Override
	public String toString() {
		return flavor + "(" + quantity + ")";
	}
}
