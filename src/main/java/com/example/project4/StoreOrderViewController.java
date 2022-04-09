package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StoreOrderViewController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	public void donutButtonClick(ActionEvent actionEvent) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("donutView.fxml"));
			root = loader.load();
			scene = new Scene(root, 600, 400);
			stage = new Stage();
			stage.setTitle("Donuts");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void coffeeButtonClick(ActionEvent actionEvent) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("coffeeView.fxml"));
			root = loader.load();
			scene = new Scene(root, 600, 400);
			stage = new Stage();
			stage.setTitle("Coffee");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ordersButtonClick(ActionEvent actionEvent) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("orderView.fxml"));
			root = loader.load();
			scene = new Scene(root, 600, 400);
			stage = new Stage();
			stage.setTitle("Your Order");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void storeOrdersButtonClick(ActionEvent actionEvent) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("storeOrderView.fxml"));
			root = loader.load();
			scene = new Scene(root, 600, 400);
			stage = new Stage();
			stage.setTitle("Store Orders");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}