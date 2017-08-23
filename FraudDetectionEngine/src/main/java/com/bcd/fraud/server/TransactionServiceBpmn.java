package com.bcd.fraud.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;

import com.bcd.fraud.Transaction;
import com.bcd.fraud.bpmn.BpmnProcess;
import com.bcd.fraud.bpmn.ProcessManager;
import com.bcd.util.Configurator;

public class TransactionServiceBpmn extends UnicastRemoteObject implements TransactionService {

	private static final long serialVersionUID = 8258034376027802315L;

	private final ProcessManager processManager;
	private final TransactionDAO transactionDAO;

	public TransactionServiceBpmn() throws RemoteException {
		processManager = new ProcessManager();
		transactionDAO = DaoFactory.getTransactionDAO(Configurator.getStorageMode());
	}

	@Override
	public boolean uploadTransaction(Transaction transaction) throws RemoteException {
		System.out
				.println("\nTransaction [id: " + transaction.getId() + " ] received. Inserting transaction in storage");
		transactionDAO.create(transaction);
		System.out.println("Running a new " + BpmnProcess.CARD_FRAUD_DETECTION + " process!");
		ProcessInstance cardFraudProcess = processManager.startProcessInstance(BpmnProcess.CARD_FRAUD_DETECTION,
				transaction);
		System.out.println("Flow for card fraud detection process [id: " + cardFraudProcess.getId() + "]");
		processManager.runProcessV2(cardFraudProcess);

		return true;
	}

	@Override
	public List<Transaction> getAllPossibleFraud() throws RemoteException {
		return transactionDAO.getAllPossibleFraud();
	}
}