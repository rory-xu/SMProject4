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
 * This class controls the donut view window and determines what happens when certain actions are made
 * @author Rory Xu, Hassan Alfareed
 */
public class DonutViewController {
	private StoreFrontViewController storeFrontViewController;
	private final DecimalFormat df = new DecimalFormat("#0.00");
	private final ObservableList<String> availableYeast = FXCollections.observableArrayList("Strawberry", "Chocolate", "Glazed");
	private final ObservableList<String> availableCake = FXCollections.observableArrayList("Cream", "Mint", "Sugar");
	private final ObservableList<String> availableHoles = FXCollections.observableArrayList("Jelly", "Cinnamon", "Regular");

	@FXML
	private ChoiceBox<String> donutTypeBox;

	@FXML
	private ComboBox<String> numberOfDonutsBox;

	@FXML
	private ListView<String> availableFlavors;

	@FXML
	private ListView<Donut> orderedFlavors;

	@FXML
	private TextField subtotal;


	/**
	 * Adds a donut from the order list
	 */
	@FXML
	void addDonutButtonClick() {
		String type = donutTypeBox.getValue();
		String flavor = availableFlavors.getSelectionModel().getSelectedItem();
		int quantity = 1;
		Donut donut = new YeastDonut(flavor, quantity);
		try {
			quantity = Integer.parseInt(numberOfDonutsBox.getValue().trim());
			if (quantity < 1) throw new NumberFormatException();
		} catch (NumberFormatException e) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setHeaderText("Invalid number!");
			a.setContentText("The number of donuts you requested is not valid, please enter a numerical value!");
			a.show();
			return;
		}
		switch (type) {
			case "Yeast Donut":
				donut = new YeastDonut(flavor, quantity);
				break;
			case "Cake Donut":
				donut = new CakeDonut(flavor, quantity);
				break;
			case "Donut Hole":
				donut = new DonutHole(flavor, quantity);
				break;
		}
		if (flavor == null) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setHeaderText("Flavor not valid!");
			a.setContentText("Please select a flavor before adding a donut to your order!");
			a.showAndWait();
		} else {
			availableFlavors.getItems().remove(flavor);
			orderedFlavors.getItems().add(donut);
		}
		updateSubtotal();
	}

	/**
	 * Deletes a donut from the order list
	 */
	@FXML
	void deleteDonutButtonClick() {
		Donut donut = orderedFlavors.getSelectionModel().getSelectedItem();
		if (donut == null) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setHeaderText("Cannot remove nothing!");
			a.setContentText("Please select an item to remove!");
			a.showAndWait();
			return;
		}
		if (donut instanceof YeastDonut) {
			availableYeast.add(donut.flavor);
			updateAvailableFlavors(donutTypeBox.getSelectionModel().getSelectedItem());
			orderedFlavors.getItems().remove(donut);
		}
		else if (donut instanceof CakeDonut) {
			availableCake.add(donut.flavor);
			updateAvailableFlavors(donutTypeBox.getSelectionModel().getSelectedItem());
			orderedFlavors.getItems().remove(donut);
		}
		else {
			availableHoles.add(donut.flavor);
			updateAvailableFlavors(donutTypeBox.getSelectionModel().getSelectedItem());
			orderedFlavors.getItems().remove(donut);
		}
		updateSubtotal();
	}

	/**
	 * Adds the list of donuts to the order list
	 * Displays an alert when attempting to add no donuts to an order
	 * @param event Closes the window upon successful addition
	 */
	@FXML
	void addDonutsToOrderButtonClick(ActionEvent event){
		if (orderedFlavors.getItems().size() < 1) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setHeaderText("No donuts in order!");
			a.setContentText("Please add donuts to the list before submitting to your order!");
			a.showAndWait();
		}
		else {
			for (int i = 0; i < orderedFlavors.getItems().size(); i++) {
				storeFrontViewController.getOrder().add(orderedFlavors.getItems().get(i));
			}
			Alert a = new Alert(Alert.AlertType.INFORMATION);
			a.setHeaderText("Donut order added!");
			a.showAndWait();
			Node source = (Node) event.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
		}
	}

	/**
	 * Detects if the donut type is changed and adjusts the available flavors accordingly
	 */
	@FXML
	void typeChanged() {
		String type = donutTypeBox.getSelectionModel().getSelectedItem();
		if (type == null) return;
		updateAvailableFlavors(type);
	}

	/**
	 * Updates the available flavors list
	 * @param type The type of donut to grab the list of available flavors from
	 */
	@FXML
	void updateAvailableFlavors(String type) {
		if (type.equals("Yeast Donut")) {
			availableFlavors.setItems(availableYeast);
		}
		else if (type.equals("Cake Donut")) {
			availableFlavors.setItems(availableCake);
		}
		else {
			availableFlavors.setItems(availableHoles);
		}
	}

	/**
	 * Updates the subtotal when a donut order is added to the donut list
	 */
	@FXML
	void updateSubtotal() {
		double newSubtotal = 0;
		for (int i = 0; i < orderedFlavors.getItems().size(); i++) {
			newSubtotal += orderedFlavors.getItems().get(i).itemPrice();
		}
		subtotal.setText(df.format(newSubtotal));
	}

	/**
	 * Initializes the donut window and pre-populates data
	 */
	public void initialize() {
		ObservableList<String> donutTypes = FXCollections.observableArrayList("Yeast Donut", "Cake Donut", "Donut Hole");
		ObservableList<String> quantity = FXCollections.observableArrayList("1", "2", "3", "4", "5");
		donutTypeBox.setItems(donutTypes);
		donutTypeBox.setValue("Yeast Donut");
		numberOfDonutsBox.setItems(quantity);
		numberOfDonutsBox.setValue("1");
		numberOfDonutsBox.setEditable(true);
		numberOfDonutsBox.getEditor().textProperty().addListener((obs, oldVal, newVal) -> numberOfDonutsBox.setValue(newVal));
		availableFlavors.setItems(availableYeast);
		subtotal.setText(df.format(0.00));
	}

	/**
	 * Sets up an access point to the storefront Controller
	 * @param storeFrontViewController The storefront controller
	 */
	public void setMainController(StoreFrontViewController storeFrontViewController) {
		this.storeFrontViewController = storeFrontViewController;
	}
}
