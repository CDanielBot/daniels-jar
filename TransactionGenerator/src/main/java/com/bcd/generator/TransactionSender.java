package com.bcd.generator;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

import com.bcd.fraud.Transaction;
import com.bcd.fraud.server.TransactionService;

public class TransactionSender {

	private final TransactionService processorStub;

	public TransactionSender() throws MalformedURLException, RemoteException, NotBoundException {
		processorStub = lookup();
	}

	public void sendTransactions(List<Transaction> transactions) throws RemoteException {
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

}
