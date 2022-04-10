package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.text.DecimalFormat;

/**
 * This class controls the order view window and determines what happens when certain actions are made
 * @author Rory Xu, Hassan Alfareed
 */
public class OrderViewController {

	private StoreFrontViewController storeFrontViewController;
	private final DecimalFormat df = new DecimalFormat("#0.00");


	@FXML
	private TextField grandTotalBox;

	@FXML
	private ListView<MenuItem> itemsInOrder;

	@FXML
	private TextField salesTaxBox;

	@FXML
	private TextField subtotalBox;

	@FXML
	void placeOrderButtonClick(ActionEvent event) {
		storeFrontViewController.getStoreOrders().add(storeFrontViewController.getOrder());
		storeFrontViewController.makeNewOrder();
		Alert a = new Alert(Alert.AlertType.INFORMATION);
		a.setHeaderText("Order complete!");
		a.showAndWait();
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	@FXML
	void removeItemButtonClick() {
		MenuItem item = itemsInOrder.getSelectionModel().getSelectedItem();
		if (item == null) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setHeaderText("No item selected!");
			a.setContentText("Please select an item to remove!");
			a.showAndWait();
		}
		else {
			itemsInOrder.getItems().remove(item);
			updateAllTotals();
		}

	}

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

	/**
	 * Sets up an access point to the store front Controller
	 * @param storeFrontViewController The storefront controller
	 */
	public void setMainController(StoreFrontViewController storeFrontViewController) {
		this.storeFrontViewController = storeFrontViewController;
	}

	/**
	 * Initializes the order view window and pre-populates data
	 */
	public void init() {
		itemsInOrder.setItems(storeFrontViewController.getOrder().getItems());
		updateAllTotals();
	}
}
