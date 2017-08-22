package com.bcd.fraud.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class MainWindow extends Application {

	private Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Fraud Detection");

		StackPane root = new StackPane();
		root.getChildren().add(getPossibleFraudButton());
		primaryStage.setScene(new Scene(root, 700, 550));
		primaryStage.show();
		
		this.primaryStage = primaryStage;
	}

	private Button getPossibleFraudButton() {
		Button btn = new Button();
		btn.setText("See possible fraud");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				PotentialFraudsTable potentialFraudTable = new PotentialFraudsTable();
				Stage tableStage = potentialFraudTable.getStage();
	            tableStage.show();
	            primaryStage.close();
			}
		});
		return btn;
	}
	
}
