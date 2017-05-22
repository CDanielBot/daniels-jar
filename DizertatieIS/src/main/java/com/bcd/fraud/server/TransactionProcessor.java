package com.bcd.fraud.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.bcd.fraud.Transaction;

public interface TransactionProcessor extends Remote {
    void uploadTransaction(Transaction transaction) throws RemoteException;
}