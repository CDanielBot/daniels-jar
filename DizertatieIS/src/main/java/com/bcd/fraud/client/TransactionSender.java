package com.bcd.fraud.client;

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
import com.bcd.fraud.server.TransactionProcessor;
import com.bcd.util.TransactionGenerator;
import com.bcd.util.TransactionGenerator.TransactionType;
import com.bcd.util.TransactionGenerator.TypeCategory;

public class TransactionSender {

	
	private final TransactionProcessor processorStub;

	public TransactionSender(String[] args) throws MalformedURLException, RemoteException, NotBoundException  {
//		String host = (args.length < 1) ? null : args[0];
//		processorStub = lookup(host);
		processorStub = (TransactionProcessor) Naming.lookup("//localhost/TransactionProcessor");
	}
	
	public void sendTransactions() throws RemoteException{
		List<Transaction> transactions = loadTransactions();
		Collections.shuffle(transactions);

		for (Transaction transaction : transactions) {
			send(transaction);
		}
	}

	private TransactionProcessor lookup(String host) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(host);
		return (TransactionProcessor) registry.lookup("TransactionProcessor");
	}

	private void send(Transaction transaction) throws RemoteException {
		processorStub.uploadTransaction(transaction);
	}

	private List<Transaction> loadTransactions() {
		System.out.println("Please provide the following:\n validNo potentialNo fraudNo \n where \n"
				+ "validNo - number of valid transactions \n"
				+ "potentialNo - number of potential fraud transactions \n"
				+ "fraudNo - number of fraud transactions \n");

		Scanner scanner = new Scanner(System.in);
		int validNo = scanner.nextInt();
		int potentialNo = scanner.nextInt();
		int fraudNo = scanner.nextInt();

		List<Transaction> transactions = new ArrayList<>();

		for (int i = 1; i <= validNo; i++) {
			transactions.add(TransactionGenerator.generate(TransactionType.Valid));
		}

		for (int i = 1; i <= potentialNo; i++) {
			transactions.add(TransactionGenerator.generate(TransactionType.random(TypeCategory.Suspect)));
		}

		for (int i = 1; i <= fraudNo; i++) {
			transactions.add(TransactionGenerator.generate(TransactionType.random(TypeCategory.Fraud)));
		}

		return transactions;
	}

}
