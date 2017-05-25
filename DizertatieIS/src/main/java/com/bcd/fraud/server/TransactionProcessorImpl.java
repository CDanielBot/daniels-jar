package com.bcd.fraud.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;

import org.activiti.engine.runtime.ProcessInstance;

import com.bcd.fraud.Transaction;
import com.bcd.fraud.bpmn.BpmnProcess;
import com.bcd.fraud.bpmn.ProcessManager;

public class TransactionProcessorImpl extends UnicastRemoteObject implements TransactionProcessor {

	private static final long serialVersionUID = 8258034376027802315L;

	private final ProcessManager processManager;

	public TransactionProcessorImpl() throws RemoteException {
		processManager = new ProcessManager();
	}

	@Override
	public void uploadTransaction(Transaction transaction) throws RemoteException {
		System.out.println("Transaction received: " + transaction.getId());
		System.out.println("Going to start a new " + BpmnProcess.CARD_FRAUD_DETECTION + " process!");
		ProcessInstance cardFraudProcess = processManager.startProcessInstance(BpmnProcess.CARD_FRAUD_DETECTION,
				transaction);
		System.out.println("Going to run " + BpmnProcess.CARD_FRAUD_DETECTION + " process with id: "
				+ cardFraudProcess.getId() + "!");
		processManager.runProcessV2(cardFraudProcess);

	}
}