package com.example.project4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class controls the store front window and determines what happens when certain actions are made
 * @author Rory Xu, Hassan Alfareed
 */
public class StoreFrontViewController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private int orderNumber = 1;
	private Order order = new Order(orderNumber);
	private StoreOrders storeOrders = new StoreOrders();

	/**
	 * Opens the donut view window
	 */
	@FXML
	public void donutButtonClick() {
		try {
			FXMLLoader donutLoader = new FXMLLoader(getClass().getResource("donutView.fxml"));
			root = donutLoader.load();
			DonutViewController donutViewController = donutLoader.getController();
			donutViewController.setMainController(this);
			scene = new Scene(root, 600, 400);
			stage = new Stage();
			stage.setTitle("Donuts");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens the coffee view window
	 */
	@FXML
	public void coffeeButtonClick() {
		try {
			FXMLLoader coffeeLoader = new FXMLLoader(getClass().getResource("coffeeView.fxml"));
			root = coffeeLoader.load();
			CoffeeViewController coffeeViewController = coffeeLoader.getController();
			coffeeViewController.setMainController(this);
			scene = new Scene(root, 600, 400);
			stage = new Stage();
			stage.setTitle("Coffee");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens the basket window
	 */
	@FXML
	public void ordersButtonClick() {
		try {
			FXMLLoader orderLoader = new FXMLLoader(getClass().getResource("orderView.fxml"));
			root = orderLoader.load();
			OrderViewController orderViewController = orderLoader.getController();
			orderViewController.setMainController(this);
			orderViewController.init();
			scene = new Scene(root, 600, 400);
			stage = new Stage();
			stage.setTitle("Your Order");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens the store orders window
	 */
	@FXML
	public void storeOrdersButtonClick() {
		try {
			FXMLLoader storeOrdersLoader = new FXMLLoader(getClass().getResource("storeOrderView.fxml"));
			root = storeOrdersLoader.load();
			StoreOrdersViewController storeOrdersViewController = storeOrdersLoader.getController();
			storeOrdersViewController.setMainController(this);
			storeOrdersViewController.init();
			scene = new Scene(root, 600, 400);
			stage = new Stage();
			stage.setTitle("Store Orders");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the current order being processed
	 * @return The current order
	 */
	@FXML
	public Order getOrder() {
		return order;
	}

	/**
	 * Retrieves the list of store orders
	 * @return The store orders
	 */
	@FXML
	public StoreOrders getStoreOrders() {
		return storeOrders;
	}

	/**
	 * Starts a fresh order
	 */
	public void makeNewOrder() {
		orderNumber++;
		order = new Order(orderNumber);
	}
}