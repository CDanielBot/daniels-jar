package com.bcd.fraud.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.bcd.fraud.Transaction;

public interface TransactionService extends Remote {
	
    boolean uploadTransaction(Transaction transaction) throws RemoteException;
    
    List<Transaction> getAllPossibleFraud() throws RemoteException; 
}