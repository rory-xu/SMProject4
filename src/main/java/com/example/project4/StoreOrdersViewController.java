package com.example.project4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class controls the store orders window and determines what happens when certain actions are made
 * @author Rory Xu, Hassan Alfareed
 */
public class StoreOrdersViewController {
	private StoreFrontViewController storeFrontViewController;

	@FXML
	private ListView<Order> storeOrdersBox;

	/**
	 * Deletes an order from the store orders list
	 */
	@FXML
	void deleteOrderButtonClick() {
		Order order = storeOrdersBox.getSelectionModel().getSelectedItem();
		if (order == null) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setHeaderText("No order selected!");
			a.setContentText("Please select an order to remove!");
			a.showAndWait();
		}
		else {
			storeFrontViewController.getStoreOrders().getOrders().remove(order);
			storeOrdersBox.setItems(storeFrontViewController.getStoreOrders().getOrders());
		}
	}

	/**
	 * Exports the store orders list to a text file
	 */
	@FXML
	void exportOrdersButtonClick() {

	}

	/**
	 * Opens a new window with the selected order's details
	 */
	@FXML
	void viewOrderDetailsButtonClick() {
		Order order = storeOrdersBox.getSelectionModel().getSelectedItem();
			try {
				FXMLLoader orderDetailsLoader = new FXMLLoader(getClass().getResource("orderDetailsView.fxml"));
				Parent root = orderDetailsLoader.load();
				OrderDetailsViewController orderDetailsViewController = orderDetailsLoader.getController();
				orderDetailsViewController.setMainController(this);
				orderDetailsViewController.init(order.getOrderNumber());
				Scene scene = new Scene(root, 600, 400);
				Stage stage = new Stage();
				stage.setTitle("Order Number" + order.getOrderNumber());
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				Alert a = new Alert(Alert.AlertType.ERROR);
				a.setHeaderText("There are no orders to show!");
				a.setContentText("Please add an order to display!");
				a.showAndWait();
			}

	}

	/**
	 * Sets up an access point to the store front Controller
	 * @param storeFrontViewController The storefront controller
	 */
	public void setMainController(StoreFrontViewController storeFrontViewController) {
		this.storeFrontViewController = storeFrontViewController;
	}

	/**
	 * Initializes the store order view window and pre-populates data
	 */
	public void init() {
		storeOrdersBox.setItems(storeFrontViewController.getStoreOrders().getOrders());
	}

	/**
	 * Retrieves the store order list
	 * @return The store order list
	 */
	public ObservableList<Order> getStoreOrdersBox() {
		return storeFrontViewController.getStoreOrders().getOrders();
	}
}
