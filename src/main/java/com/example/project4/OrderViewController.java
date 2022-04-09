package com.example.project4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class OrderViewController {

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
		subtotalBox.setText(Double.toString(subTotal));
		double salesTax = Double.parseDouble(df.format(Double.toString(subTotal * 0.0625)));
		salesTaxBox.setText(Double.toString(salesTax));
		double grandTotal = subTotal + salesTax;
		grandTotalBox.setText(Double.toString(grandTotal));
	}

}
