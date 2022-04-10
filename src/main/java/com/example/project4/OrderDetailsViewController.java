package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class OrderDetailsViewController {

	private StoreOrdersViewController storeOrdersViewController;
	private DecimalFormat df = new DecimalFormat("#0.00");


	@FXML
	private TextField grandTotalBox;

	@FXML
	private ListView<MenuItem> itemsInOrder;

	@FXML
	private Button placeOrderButton;

	@FXML
	private Button removeItemButton;

	@FXML
	private TextField salesTaxBox;

	@FXML
	private TextField subtotalBox;

	@FXML
	void updateAllTotals() {
		double subTotal = 0.00;
		for (int i = 0; i < itemsInOrder.getItems().size(); i++) {
			subTotal += itemsInOrder.getItems().get(i).itemPrice();
		}
		subtotalBox.setText(df.format(subTotal));
		salesTaxBox.setText(df.format(subTotal * 0.0625));
		grandTotalBox.setText(df.format(subTotal + subTotal * 0.0625));
	}

	public void setMainController(StoreOrdersViewController storeOrdersViewController) {
		this.storeOrdersViewController = storeOrdersViewController;
	}

	public void init(int orderNumber) {
		for (int i = 0; i < storeOrdersViewController.getStoreOrdersBox().size(); i++) {
			if (storeOrdersViewController.getStoreOrdersBox().get(i).getOrderNumber() == orderNumber) {
				itemsInOrder.setItems(storeOrdersViewController.getStoreOrdersBox().get(i).getItems());
			}
		}
		updateAllTotals();
	}
}
