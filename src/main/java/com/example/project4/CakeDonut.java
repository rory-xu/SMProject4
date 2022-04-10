package com.example.project4;

/**
 * A Cake Donut object with specific flavors
 * @author Rory Xu, Hassan Alfareed
 */
public class CakeDonut extends Donut {

	/**
	 * Constructs a cake donut
	 * @param flavor The flavor of the donut
	 * @param quantity The number of this type of donut
	 */
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
