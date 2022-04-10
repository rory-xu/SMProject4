package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class StoreOrders implements Customizable{

	private ObservableList<Order> items= FXCollections.observableArrayList();

	@Override
	public boolean add(Object obj) {
		return false;
	}

	@Override
	public boolean remove(Object obj) {
		return false;
	}
}
