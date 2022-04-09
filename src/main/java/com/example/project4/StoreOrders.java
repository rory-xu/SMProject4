package com.example.project4;

import java.util.ArrayList;
import java.util.List;

public class StoreOrders implements Customizable{

	private List<Order> items= new ArrayList<>();

	@Override
	public boolean add(Object obj) {
		return false;
	}

	@Override
	public boolean remove(Object obj) {
		return false;
	}
}
