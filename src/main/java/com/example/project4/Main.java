package com.example.project4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the main class that runs the project
 * @author Rory Xu, Hassan Alfareed
 */
public class Main extends Application {

	/**
	 * Sets the stage of the main menu of the window
	 * @param stage The main menu of the café
	 * @throws IOException Catches errors loading the program
	 */
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("storeFrontView.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 600, 400);
		stage.setTitle("Rutgers Cafe");
		stage.setScene(scene);
		stage.show();
		stage.setOnCloseRequest(e -> Platform.exit());
	}

	/**
	 * Runs the project
	 * @param args Not used
	 */
	public static void main(String[] args) {
		launch();
	}
}