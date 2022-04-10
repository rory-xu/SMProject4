package com.example.project4;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;

/**
 * This class controls the order details view window and determines what happens when certain actions are made
 * @author Rory Xu, Hassan Alfareed
 */
public class OrderDetailsViewController {

	private StoreOrdersViewController storeOrdersViewController;
	private final DecimalFormat df = new DecimalFormat("#0.00");


	@FXML
	private TextField grandTotalBox;

	@FXML
	private ListView<MenuItem> itemsInOrder;

	@FXML
	private TextField salesTaxBox;

	@FXML
	private TextField subtotalBox;

	/**
	 * Displays the monetary information of the order
	 */
	@FXML
	void displayTotals() {
		double subTotal = 0.00;
		for (int i = 0; i < itemsInOrder.getItems().size(); i++) {
			subTotal += itemsInOrder.getItems().get(i).itemPrice();
		}
		subtotalBox.setText(df.format(subTotal));
		salesTaxBox.setText(df.format(subTotal * 0.0625));
		grandTotalBox.setText(df.format(subTotal + subTotal * 0.0625));
	}

	/**
	 * Sets up an access point to the store orders Controller
	 * @param storeOrdersViewController The storefront controller
	 */
	public void setMainController(StoreOrdersViewController storeOrdersViewController) {
		this.storeOrdersViewController = storeOrdersViewController;
	}

	/**
	 * Initializes the order details window and pre-populates data
	 * @param orderNumber The order number of the order to be populated
	 */
	public void init(int orderNumber) {
		for (int i = 0; i < storeOrdersViewController.getStoreOrdersBox().size(); i++) {
			if (storeOrdersViewController.getStoreOrdersBox().get(i).getOrderNumber() == orderNumber) {
				itemsInOrder.setItems(storeOrdersViewController.getStoreOrdersBox().get(i).getItems());
			}
		}
		displayTotals();
	}
}
