package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DecimalFormat;

public class CoffeeViewController {

	private DecimalFormat df = new DecimalFormat("#0.00");
	Coffee coffee = new Coffee(1.69);

	@FXML
	private Button addCoffeeToOrderButton;

	@FXML
	private CheckBox caramelCheckBox;

	@FXML
	private ChoiceBox<String> coffeeSizeBox;

	@FXML
	private TextField coffeeSubtotal;

	@FXML
	private CheckBox creamCheckBox;

	@FXML
	private CheckBox milkCheckBox;

	@FXML
	private ComboBox<String> numberOfCoffeeBox;

	@FXML
	private CheckBox syrupCheckBox;

	@FXML
	private CheckBox whippedCreamCheckBox;

	@FXML
	void caramelCheckBoxClicked(ActionEvent event) {
		if (caramelCheckBox.isSelected()) {
			coffee.add("Caramel");
			updateSubtotal();
		}
		else {
			coffee.remove("Caramel");
			updateSubtotal();
		}
	}

	@FXML
	void creamCheckBoxClick(ActionEvent event) {
		if (creamCheckBox.isSelected()) {
			coffee.add("Cream");
			updateSubtotal();
		}
		else {
			coffee.remove("Cream");
			updateSubtotal();
		}
	}

	@FXML
	void milkCheckBoxClick(ActionEvent event) {
		if (milkCheckBox.isSelected()) {
			coffee.add("Milk");
			updateSubtotal();
		}
		else {
			coffee.remove("Milk");
			updateSubtotal();
		}
	}

	@FXML
	void syrupCheckBoxClicked(ActionEvent event) {
		if (syrupCheckBox.isSelected()) {
			coffee.add("Syrup");
			updateSubtotal();
		}
		else {
			coffee.remove("Syrup");
			updateSubtotal();
		}
	}

	@FXML
	void whippedCreamCheckBoxClicked(ActionEvent event) {
		if (whippedCreamCheckBox.isSelected()) {
			coffee.add("Whipped Cream");
			updateSubtotal();
		}
		else {
			coffee.remove("Whipped Cream");
			updateSubtotal();
		}
	}

	@FXML
	public void typeChanged(ActionEvent actionEvent) {
		String size = coffeeSizeBox.getSelectionModel().getSelectedItem();
		if (size == null) return;
		if (size.equals("Short")) {
			coffee.changeBasePrice(1.69);
			updateSubtotal();
		}
		else if (size.equals("Tall")) {
			coffee.changeBasePrice(2.09);
			updateSubtotal();
		}
		else if (size.equals("Grande")) {
			coffee.changeBasePrice(2.49);
			updateSubtotal();
		}
		else {
			coffee.changeBasePrice(2.89);
			updateSubtotal();
		}
	}

	@FXML
	void numberOfCoffeeChanged(ActionEvent event) {
		if (numberOfCoffeeBox.getValue().trim() == null || numberOfCoffeeBox.getValue().trim().equals("")) return;
		try {
			coffee.changeQuantity(Integer.parseInt(numberOfCoffeeBox.getValue().trim()));
			updateSubtotal();
		} catch (NumberFormatException e) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setHeaderText("Invalid number!");
			a.setContentText("The number of coffees you requested is not valid, please enter a numerical value!");
			a.show();
		}
	}

	@FXML
	void addCoffeeToOrderButtonClick(ActionEvent event) {

	}

	@FXML
	void updateSubtotal() {
		coffeeSubtotal.setText(df.format(coffee.itemPrice()));
	}

	@FXML
	void initialize() {
		ObservableList<String> coffeeSizes = FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti");
		ObservableList<String> quantity = FXCollections.observableArrayList("1", "2", "3", "4", "5");
		coffeeSizeBox.setItems(coffeeSizes);
		coffeeSizeBox.setValue("Short");
		numberOfCoffeeBox.setItems(quantity);
		numberOfCoffeeBox.setValue("1");
		numberOfCoffeeBox.setEditable(true);
		numberOfCoffeeBox.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {numberOfCoffeeBox.setValue(newVal);});
		coffeeSubtotal.setText(df.format(coffee.itemPrice()));

	}
}
