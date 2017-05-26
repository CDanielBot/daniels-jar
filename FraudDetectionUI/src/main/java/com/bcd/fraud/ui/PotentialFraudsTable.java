package com.bcd.fraud.ui;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bcd.fraud.Transaction;
import com.bcd.fraud.server.TransactionService;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

@SuppressWarnings("restriction")
public class PotentialFraudsTable extends Application {

	private TableView<TransactionVO> transactionsTable = new TableView<>();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		loadTableConfig(stage);
		loadTableData();
	}

	private void loadTableConfig(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Table View Sample");
		stage.setWidth(300);
		stage.setHeight(500);

		final Label label = new Label("Address Book");
		label.setFont(new Font("Arial", 20));

		transactionsTable.setEditable(false);
		transactionsTable.getColumns().addAll(ColumnsGenerator.generateColumns());

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, transactionsTable);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}

	private void loadTableData() {
		TransactionService transactionService = lookupTransactionService();
		try {
			List<Transaction> possibleFraudTransactions = transactionService.getAllPossibleFraud();
			List<TransactionVO> transactions = new ArrayList<>();
			for (Transaction t : possibleFraudTransactions) {
				transactions.add(new TransactionVO(t));
			}
			ObservableList<TransactionVO> tableData = FXCollections.observableList(transactions);
			transactionsTable.setItems(tableData);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private TransactionService lookupTransactionService() {
		try {
			return (TransactionService) Naming.lookup("//localhost/TransactionService");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static class ColumnsGenerator {

		static List<TableColumn<TransactionVO, String>> generateColumns() {

			TableColumn<TransactionVO, String> idCol = new TableColumn<>("Id");
			idCol.setMinWidth(150);
			idCol.setCellValueFactory(new PropertyValueFactory<TransactionVO, String>("id"));

			TableColumn<TransactionVO, String> typeCol = new TableColumn<>("Type");
			typeCol.setMinWidth(70);
			typeCol.setCellValueFactory(new PropertyValueFactory<TransactionVO, String>("type"));

			TableColumn<TransactionVO, String> amountCol = new TableColumn<>("Amount");
			amountCol.setMinWidth(70);
			amountCol.setCellValueFactory(new PropertyValueFactory<TransactionVO, String>("amount"));

			return Arrays.asList(idCol, typeCol, amountCol);

		}
	}
}