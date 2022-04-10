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

	/**
	 * Calculates and returns the price of the donut(s)
	 * @return The price of the donut(s)
	 */
	@Override
	public double itemPrice() {
		return 1.79 * quantity;
	}

	/**
	 * Displays the donut order
	 * @return The order in text form
	 */
	@Override
	public String toString() {
		return flavor + "(" + quantity + ")";
	}
}
