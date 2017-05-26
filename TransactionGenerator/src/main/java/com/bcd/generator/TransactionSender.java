package com.bcd.generator;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.bcd.fraud.Transaction;
import com.bcd.fraud.server.TransactionService;

public class TransactionSender {

	private final TransactionService processorStub;

	public TransactionSender(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		processorStub = lookup();
	}

	public void sendTransactions() throws RemoteException {
		List<Transaction> transactions = loadTransactions();
		Collections.shuffle(transactions);

		for (Transaction transaction : transactions) {
			send(transaction);
		}
	}

	private TransactionService lookup() throws RemoteException, NotBoundException, MalformedURLException {
		return (TransactionService) Naming.lookup("//localhost/TransactionService");
	}

	private void send(Transaction transaction) throws RemoteException {
		processorStub.uploadTransaction(transaction);
	}

	private List<Transaction> loadTransactions() {

		NumberOfTransactions noOfTransactions = readFromCmdLine();

		List<Transaction> transactions = new ArrayList<>();

		for (int i = 1; i <= noOfTransactions.getValid(); i++) {
			transactions.add(TransactionGenerator.generate(TransactionGenerator.TransactionType.Valid));
		}

		for (int i = 1; i <= noOfTransactions.getPotentialOfFraud(); i++) {
			transactions.add(TransactionGenerator
					.generate(TransactionGenerator.TransactionType.random(TransactionGenerator.TypeCategory.Suspect)));
		}

		for (int i = 1; i <= noOfTransactions.getFraud(); i++) {
			transactions.add(TransactionGenerator
					.generate(TransactionGenerator.TransactionType.random(TransactionGenerator.TypeCategory.Fraud)));
		}

		return transactions;
	}

	private NumberOfTransactions readFromCmdLine() {

		NumberOfTransactions result = new NumberOfTransactions();

		System.out.println("Please provide the following:\n validNo potentialNo fraudNo \n where \n"
				+ "validNo - number of valid transactions \n"
				+ "potentialNo - number of potential fraud transactions \n"
				+ "fraudNo - number of fraud transactions \n");

		try (Scanner scanner = new Scanner(System.in)) {
			result.setValid(scanner.nextInt());
			result.setPotentialOfFraud(scanner.nextInt());
			result.setFraud(scanner.nextInt());
		}

		return result;
	}

	private static class NumberOfTransactions {

		private int valid;
		private int potentialOfFraud;
		private int fraud;

		public int getValid() {
			return valid;
		}

		public void setValid(int valid) {
			this.valid = valid;
		}

		public int getPotentialOfFraud() {
			return potentialOfFraud;
		}

		public void setPotentialOfFraud(int potentialOfFraud) {
			this.potentialOfFraud = potentialOfFraud;
		}

		public int getFraud() {
			return fraud;
		}

		public void setFraud(int fraud) {
			this.fraud = fraud;
		}

	}

}
