package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

public class DonutViewController implements Initializable {
	private StoreFrontViewController storeFrontViewController;
	private DecimalFormat df = new DecimalFormat("#0.00");
	private ObservableList<String> availableYeast = FXCollections.observableArrayList("Strawberry", "Chocolate", "Glazed");
	private ObservableList<String> availableCake = FXCollections.observableArrayList("Cream", "Mint", "Sugar");
	private ObservableList<String> availableHoles = FXCollections.observableArrayList("Jelly", "Cinnamon", "Regular");

	@FXML
	private Button addDonut;

	@FXML
	private Button addDonutsToOrder;

	@FXML
	private Button deleteDonut;

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


	@FXML
	void addDonutButtonClick(ActionEvent event) {
		String type = donutTypeBox.getValue();
		Donut donut;
		String flavor = availableFlavors.getSelectionModel().getSelectedItem();
		switch(type) {
			case "Yeast Donut":
				try {
					int quantity = Integer.parseInt(numberOfDonutsBox.getValue().trim());
					donut = new YeastDonut(flavor, quantity);
					if (flavor == null) {
						Alert a = new Alert(Alert.AlertType.ERROR);
						a.setHeaderText("Flavor not valid!");
						a.setContentText("Please select a flavor before adding a donut to your order!");
						a.showAndWait();
					}
					else {
						availableFlavors.getItems().remove(flavor);
						orderedFlavors.getItems().add(donut);
					}
				} catch (NumberFormatException e) {
					Alert a = new Alert(Alert.AlertType.ERROR);
					a.setHeaderText("Invalid number!");
					a.setContentText("The number of donuts you requested is not valid, please enter a numerical value!");
					a.show();
				}
				break;

			case "Cake Donut":
				try {
					int quantity = Integer.parseInt(numberOfDonutsBox.getValue().trim());
					donut = new CakeDonut(flavor, quantity);
					if (flavor == null) {
						Alert a = new Alert(Alert.AlertType.ERROR);
						a.setHeaderText("Flavor not valid!");
						a.setContentText("Please select a flavor before adding a donut to your order!");
						a.showAndWait();
					}
					else {
						availableFlavors.getItems().remove(flavor);
						orderedFlavors.getItems().add(donut);
					}
				} catch (NumberFormatException e) {
					Alert a = new Alert(Alert.AlertType.ERROR);
					a.setHeaderText("Invalid number!");
					a.setContentText("The number of donuts you requested is not valid, please enter a numerical value!");
					a.show();
				}
				break;

			case "Donut Hole":
				try {
					int quantity = Integer.parseInt(numberOfDonutsBox.getValue().trim());
					donut = new DonutHole(flavor, quantity);
					if (flavor == null) {
						Alert a = new Alert(Alert.AlertType.ERROR);
						a.setHeaderText("Flavor not valid!");
						a.setContentText("Please select a flavor before adding a donut to your order!");
						a.showAndWait();
					}
					else {
						availableFlavors.getItems().remove(flavor);
						orderedFlavors.getItems().add(donut);
					}
				} catch (NumberFormatException e) {
					Alert a = new Alert(Alert.AlertType.ERROR);
					a.setHeaderText("Invalid number!");
					a.setContentText("The number of donuts you requested is not valid, please enter a numerical value!");
					a.show();
				}
				break;
		}
		updateSubtotal();
	}

	@FXML
	void deleteDonutButtonClick(ActionEvent event) {
		Donut donut = orderedFlavors.getSelectionModel().getSelectedItem();
		if (donut == null) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setHeaderText("Cannot remove nothing!");
			a.setContentText("Please select an item to remove!");
			a.showAndWait();
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

	@FXML
	void addDonutsToOrderButtonClick(ActionEvent event) throws IOException {
		for (int i = 0; i < orderedFlavors.getItems().size(); i++) {
			storeFrontViewController.getOrder().add(orderedFlavors.getItems().get(i));
		}
	}

	@FXML
	void typeChanged(ActionEvent actionEvent) {
		String type = donutTypeBox.getSelectionModel().getSelectedItem();
		if (type == null) return;
		updateAvailableFlavors(type);
	}

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

	@FXML
	void updateSubtotal() {
		double newSubtotal = 0;
		for (int i = 0; i < orderedFlavors.getItems().size(); i++) {
			newSubtotal += orderedFlavors.getItems().get(i).itemPrice();
		}
		subtotal.setText(df.format(newSubtotal));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ObservableList<String> donutTypes = FXCollections.observableArrayList("Yeast Donut", "Cake Donut", "Donut Hole");
		ObservableList<String> quantity = FXCollections.observableArrayList("1", "2", "3", "4", "5");
		donutTypeBox.setItems(donutTypes);
		donutTypeBox.setValue("Yeast Donut");
		numberOfDonutsBox.setItems(quantity);
		numberOfDonutsBox.setValue("1");
		numberOfDonutsBox.setEditable(true);
		numberOfDonutsBox.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {numberOfDonutsBox.setValue(newVal);});
		availableFlavors.setItems(availableYeast);
		subtotal.setText(df.format(0.00));
	}


	public void setMainController(StoreFrontViewController storeFrontViewController) {
		this.storeFrontViewController = storeFrontViewController;
	}
}
