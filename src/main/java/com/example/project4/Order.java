package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Order implements Customizable{
	private final int orderNumber;
	private ObservableList<MenuItem> items= FXCollections.observableArrayList();

	public Order(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public ObservableList<MenuItem> getItems() {
		return items;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	@Override
	public boolean add(Object obj) {
		if (obj instanceof MenuItem) {
			items.add((MenuItem) obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object obj) {
		if (obj instanceof MenuItem) {
			items.remove((MenuItem) obj);
			return true;
		}
		return false;
	}
}
