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
	
	public Stage getStage(){
		 Stage stage = new Stage();
		 start(stage);
		 return stage;
	}

	@Override
	public void start(Stage stage) {
		loadTableConfig(stage);
		loadTableData();
	}

	private void loadTableConfig(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Pottential Fraud Table");
		stage.setWidth(1200);
		stage.setHeight(700);

		final Label label = new Label("Potential fraud transactions");
		label.setFont(new Font("Arial", 20));

		transactionsTable.setEditable(false);
		transactionsTable.setMinSize(1000, 600);
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
			idCol.setMinWidth(250);
			idCol.setCellValueFactory(new PropertyValueFactory<TransactionVO, String>("id"));

			TableColumn<TransactionVO, String> typeCol = new TableColumn<>("Type");
			typeCol.setMinWidth(120);
			typeCol.setCellValueFactory(new PropertyValueFactory<TransactionVO, String>("type"));

			TableColumn<TransactionVO, String> amountCol = new TableColumn<>("Amount");
			amountCol.setMinWidth(100);
			amountCol.setCellValueFactory(new PropertyValueFactory<TransactionVO, String>("amount"));
			
			TableColumn<TransactionVO, String> dateTimeCol = new TableColumn<>("Date/Time");
			dateTimeCol.setMinWidth(150);
			dateTimeCol.setCellValueFactory(new PropertyValueFactory<TransactionVO, String>("dateTime"));

			TableColumn<TransactionVO, String> accountNumberCol = new TableColumn<>("Account Number");
			accountNumberCol.setMinWidth(180);
			accountNumberCol.setCellValueFactory(new PropertyValueFactory<TransactionVO, String>("accountNumber"));
			
			return Arrays.asList(idCol, typeCol, amountCol, dateTimeCol, accountNumberCol);

		}
	}
}