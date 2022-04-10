package com.example.project4;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * This class controls the store orders window and determines what happens when certain actions are made
 * @author Rory Xu, Hassan Alfareed
 */
public class StoreOrdersViewController {
	private StoreFrontViewController storeFrontViewController;
	private final DecimalFormat df = new DecimalFormat("#0.00");

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
			Alert a = new Alert(Alert.AlertType.INFORMATION);
			a.setHeaderText("Order removed!");
			a.showAndWait();
		}
	}

	/**
	 * Exports the store orders list to a text file upon the button being clicked
	 */
	@FXML
	void exportOrdersButtonClick() {
		if (storeOrdersBox.getItems().size() < 1) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("No orders to export!");
			alert.setContentText("Please add an order before attempting to export!");
			alert.showAndWait();
		}
		else {
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Please select target file for export");
			chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
					new FileChooser.ExtensionFilter("All Files", "*.*"));
			Stage stage = new Stage();
			File targetFile = chooser.showSaveDialog(stage);
			if (targetFile == null) return;
			exportDB(targetFile.getAbsolutePath());
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Orders successfully exported!");
			alert.showAndWait();
		}
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
	 * Uses a Filewriter to output lines to a file
	 * @param file The target file path
	 */
	@FXML
	public void exportDB(String file) {
		try{
			FileWriter writer = new FileWriter(file);
			for (int i = 0; i < storeOrdersBox.getItems().size(); i++) {
				Order currOrder = storeOrdersBox.getItems().get(i);
				writer.write(String.valueOf(currOrder));
				writer.write("\n\n");
				for (int j = 0; j < currOrder.getItems().size(); j++) {
					MenuItem currItem = currOrder.getItems().get(j);
					writer.write(String.valueOf(currItem));
					writer.write("\n");
				}
				writer.write("\n");
				double subtotal = 0.00;
				for (int j = 0; j < currOrder.getItems().size(); j++) {
					subtotal += currOrder.getItems().get(j).itemPrice();
				}
				writer.write("Sub-total: " + df.format(subtotal) + "\n");
				writer.write("Sales Tax: " + df.format(subtotal * 0.0625) + "\n");
				writer.write("Grand Total: " + df.format(subtotal + subtotal * 0.0625) + "\n\n");
			}
			writer.close();
		} catch (IOException e) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setHeaderText("That file path is invalid!");
			a.setContentText("Please select a file to write to!");
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
