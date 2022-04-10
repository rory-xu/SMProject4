package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.text.DecimalFormat;

/**
 * This class controls the coffee view window and determines what happens when certain actions are made
 * @author Rory Xu, Hassan Alfareed
 */
public class CoffeeViewController {

	private StoreFrontViewController storeFrontViewController;
	private DecimalFormat df = new DecimalFormat("#0.00");
	Coffee coffee = new Coffee(1.69);

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

	/**
	 * Adds/removes caramel as an add-in to/from the coffee
	 */
	@FXML
	void caramelCheckBoxClicked() {
		if (caramelCheckBox.isSelected()) {
			coffee.add("Caramel");
			updateSubtotal();
		}
		else {
			coffee.remove("Caramel");
			updateSubtotal();
		}
	}

	/**
	 * Adds/removes cream as an add-in to/from the coffee
	 */
	@FXML
	void creamCheckBoxClick() {
		if (creamCheckBox.isSelected()) {
			coffee.add("Cream");
			updateSubtotal();
		}
		else {
			coffee.remove("Cream");
			updateSubtotal();
		}
	}

	/**
	 * Adds/removes milk as an add-in to/from the coffee
	 */
	@FXML
	void milkCheckBoxClick() {
		if (milkCheckBox.isSelected()) {
			coffee.add("Milk");
			updateSubtotal();
		}
		else {
			coffee.remove("Milk");
			updateSubtotal();
		}
	}

	/**
	 * Adds/removes syrup as an add-in to/from the coffee
	 */
	@FXML
	void syrupCheckBoxClicked() {
		if (syrupCheckBox.isSelected()) {
			coffee.add("Syrup");
			updateSubtotal();
		}
		else {
			coffee.remove("Syrup");
			updateSubtotal();
		}
	}

	/**
	 * Adds/removes whipped cream as an add-in to/from the coffee
	 * @param event When the checkbox is interacted with
	 */
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

	/**
	 * Detects when the size of the coffee is changed and modifies the base price of the coffee accordingly
	 */
	@FXML
	public void sizeChanged() {
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

	/**
	 * Detects when the number of coffee(s) request changes, and also ensures that only numerical values can be manually
	 * inputted
	 */
	@FXML
	void numberOfCoffeeChanged() {
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

	/**
	 * When the add to order button is clicked, the coffee is added to the order basket
	 * @param event Closes the window after a successful addition of coffee
	 */
	@FXML
	void addCoffeeToOrderButtonClick(ActionEvent event) {
		storeFrontViewController.getOrder().add(coffee);
		Alert a = new Alert(Alert.AlertType.INFORMATION);
		a.setHeaderText("Coffee order added!");
		a.showAndWait();
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	/**
	 * Updates the subtotal display to reflect the current cost of the customized coffee
	 */
	@FXML
	void updateSubtotal() {
		coffeeSubtotal.setText(df.format(coffee.itemPrice()));
	}

	/**
	 * Initializes the coffee window and pre-populates data
	 */
	public void initialize() {
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

	/**
	 * Sets up an access point to the storefront Controller
	 * @param storeFrontViewController The storefront controller
	 */
	public void setMainController(StoreFrontViewController storeFrontViewController) {
		this.storeFrontViewController = storeFrontViewController;
	}
}
