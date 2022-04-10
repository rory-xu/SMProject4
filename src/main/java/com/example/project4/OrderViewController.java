package com.example.project4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

public class OrderViewController {

	private StoreFrontViewController storeFrontViewController;
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
	void placeOrderButtonClick(ActionEvent event) {
		storeFrontViewController.getStoreOrders().add(storeFrontViewController.getOrder());
		storeFrontViewController.makeNewOrder();
		Alert a = new Alert(Alert.AlertType.CONFIRMATION);
		a.setHeaderText("Order complete!");
		a.showAndWait();
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	@FXML
	void removeItemButtonClick(ActionEvent event) {
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

	public void setMainController(StoreFrontViewController storeFrontViewController) {
		this.storeFrontViewController = storeFrontViewController;
	}

	public void init() {
		itemsInOrder.setItems(storeFrontViewController.getOrder().getItems());
		updateAllTotals();
	}
}
