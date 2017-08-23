package com.bcd.generator;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bcd.fraud.Transaction;

public class Client {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		Client client = new Client();
		NumberOfTransactions noOfTransactions = client.readFromCmdLine();
		List<Transaction> transactions = client.loadTransactions(noOfTransactions);

		TransactionSender sender = new TransactionSender();
		sender.sendTransactions(transactions);
	}

	private List<Transaction> loadTransactions(NumberOfTransactions noOfTransactions) {
		List<Transaction> transactions = new ArrayList<>();

		for (int i = 1; i <= noOfTransactions.getValid(); i++) {
			transactions.add(TransactionGenerator.generate(TransactionGenerator.TransactionType.Valid));
		}

		for (int i = 1; i <= noOfTransactions.getSuspectOfFraud(); i++) {
			transactions.add(TransactionGenerator.generate(TransactionGenerator.TypeCategory.SuspectOfFraud.random()));
		}

		return transactions;
	}

	private NumberOfTransactions readFromCmdLine() {

		NumberOfTransactions result = new NumberOfTransactions();

		System.out.println("Please provide the following:\n x y \n where \n"
				+ "x - number of valid transactions and \n"
				+ "y - number of potential fraud transactions");

		try (Scanner scanner = new Scanner(System.in)) {
			result.setValid(scanner.nextInt());
			result.setSuspectOfFraud(scanner.nextInt());
		}

		return result;
	}

	private static class NumberOfTransactions {

		private int valid;
		private int suspectOfFraud;

		public int getValid() {
			return valid;
		}

		public void setValid(int valid) {
			this.valid = valid;
		}

		public int getSuspectOfFraud() {
			return suspectOfFraud;
		}

		public void setSuspectOfFraud(int potentialOfFraud) {
			this.suspectOfFraud = potentialOfFraud;
		}

	}

}
