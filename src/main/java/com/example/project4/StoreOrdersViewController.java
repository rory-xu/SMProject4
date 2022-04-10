package com.example.project4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class StoreOrdersViewController {
	private StoreFrontViewController storeFrontViewController;

	@FXML
	private Button deleteOrder;

	@FXML
	private Button exportOrdersButton;

	@FXML
	private ListView<Order> storeOrdersBox;

	@FXML
	private Button viewOrderDetailsButton;

	@FXML
	void deleteOrderButtonClick(ActionEvent event) {
		Order order = storeOrdersBox.getSelectionModel().getSelectedItem();
		storeFrontViewController.getStoreOrders().getOrders().remove(order);
		storeOrdersBox.setItems(storeFrontViewController.getStoreOrders().getOrders());
	}

	@FXML
	void exportOrdersButtonClick(ActionEvent event) {

	}

	@FXML
	void viewOrderDetailsButtonClick(ActionEvent event) throws IOException{
		Order order = storeOrdersBox.getSelectionModel().getSelectedItem();
			try {
				FXMLLoader orderDetailsLoader = new FXMLLoader(getClass().getResource("orderDetailsView.fxml"));
				Parent root = orderDetailsLoader.load();
				OrderDetailsViewController orderDetailsViewController = orderDetailsLoader.getController();
				orderDetailsViewController.setMainController(this);
				orderDetailsViewController.init(order.getOrderNumber());
				Scene scene = new Scene(root, 600, 400);
				Stage stage = new Stage();
				stage.setTitle("Store Orders");
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}

	public void setMainController(StoreFrontViewController storeFrontViewController) {
		this.storeFrontViewController = storeFrontViewController;
	}

	public void init() {
		storeOrdersBox.setItems(storeFrontViewController.getStoreOrders().getOrders());
	}

	public ObservableList<Order> getStoreOrdersBox() {
		return storeFrontViewController.getStoreOrders().getOrders();
	}
}
