package com.bcd.fraud.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.bcd.fraud.Transaction;
        
public class TransactionProcessorImpl extends UnicastRemoteObject implements TransactionProcessor {
        
	private static final long serialVersionUID = 8258034376027802315L;

	public TransactionProcessorImpl() throws RemoteException {}
        
	@Override
	public void uploadTransaction(Transaction transaction) throws RemoteException {
		//TODO
		System.out.println("Transaction received: " + transaction.getId());
	}
}