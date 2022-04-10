package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class StoreOrders implements Customizable{

	private ObservableList<Order> orders= FXCollections.observableArrayList();

	public ObservableList<Order> getOrders() {
		return orders;
	}

	@Override
	public boolean add(Object obj) {
		if (obj instanceof Order) {
			orders.add((Order) obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object obj) {
		if (obj instanceof Order) {
			orders.remove((Order) obj);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "";
	}
}
