/**
 * Constant add-ins for coffee
 * @author Rory Xu, Hassan Alfareed
 */
package com.example.project4;

public enum AddIns {
	CREAM("Cream", 0.30),
	SYRUP("Syrup", 0.30),
	MILK("Milk", 0.30),
	CARAMEL("Caramel", 0.30),
	WHIPPED("Whipped Cream", 0.30);

	private final String name;
	private final double cost;

	AddIns(String name, double cost) {
		this.name = name;
		this.cost = cost;
	}

	public double getCost() {
		return this.cost;
	}

	@Override
	public String toString() {
		return name;
	}
}
