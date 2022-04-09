package com.example.project4;

public abstract class Donut extends MenuItem{
	protected String flavor;
	protected int quantity;

	public Donut(String flavor, int quantity) {
		this.flavor = flavor;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return flavor + "(" + quantity + ")";
	}
}
