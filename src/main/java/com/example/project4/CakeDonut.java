/**
 * A Cake Donut object with specific flavors
 * @author Rory Xu, Hassan Alfareed
 */
package com.example.project4;

public class CakeDonut extends Donut {

	public CakeDonut(String flavor, int quantity) {
		super(flavor, quantity);
	}

	@Override
	public double itemPrice() {
		return 1.79 * quantity;
	}

	@Override
	public String toString() {
		return flavor + "(" + quantity + ")";
	}
}
