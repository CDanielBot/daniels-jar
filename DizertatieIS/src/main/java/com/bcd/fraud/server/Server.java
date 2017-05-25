package com.bcd.fraud.server;

import java.rmi.Naming;

import com.bcd.fraud.bpmn.BpmnProcess;
import com.bcd.fraud.bpmn.ProcessManager;

public class Server {

	public static void main(String args[]) {
		Server server = new Server();
		server.init();
		System.err.println("Server ready");
	}
	private final ProcessManager processManager;
	
	private Server() {
		processManager = new ProcessManager();
	}

	private void init() {
		makeServicePublic();
		deployBpmnProcesses();
	}
	

	private void deployBpmnProcesses(){
		processManager.deployProcess(BpmnProcess.CARD_FRAUD_DETECTION);
	}
	
	private void makeServicePublic(){
		try {
			Naming.rebind("//localhost/TransactionProcessor", new TransactionProcessorImpl());
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
