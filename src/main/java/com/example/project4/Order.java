package com.example.project4;

import java.util.ArrayList;
import java.util.List;

public class Order implements Customizable{
	private int orderNumber;
	private List<MenuItem> items= new ArrayList<>();

	public Order(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	public boolean add(Object obj) {
		return false;
	}

	@Override
	public boolean remove(Object obj) {
		return false;
	}
}
