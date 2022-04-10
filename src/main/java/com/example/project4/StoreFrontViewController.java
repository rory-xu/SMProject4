package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StoreFrontViewController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	private int orderNumber = 1;
	private Order order = new Order(orderNumber);
	private StoreOrders storeOrders = new StoreOrders();

	@FXML
	public void donutButtonClick(ActionEvent actionEvent) throws IOException {
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

	@FXML
	public void coffeeButtonClick(ActionEvent actionEvent) throws IOException {
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

	@FXML
	public void ordersButtonClick(ActionEvent actionEvent) throws IOException {
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

	@FXML
	public void storeOrdersButtonClick(ActionEvent actionEvent) throws IOException {
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

	@FXML
	public Order getOrder() {
		return order;
	}

	@FXML
	public StoreOrders getStoreOrders() {
		return storeOrders;
	}

	public void makeNewOrder() {
		orderNumber++;
		order = new Order(orderNumber);
	}
}