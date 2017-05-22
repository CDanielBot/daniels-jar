package com.bcd.fraud.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	public static void main(String args[]) {
		init();
	}

	private static void init() {
		try {
			Naming.rebind("//localhost/TransactionProcessor", new TransactionProcessorImpl());
			// TransactionProcessorImpl obj = new TransactionProcessorImpl();
			// TransactionProcessor stub = (TransactionProcessor)
			// UnicastRemoteObject.exportObject(obj, 0);
			//
			// // Bind the remote object's stub in the registry
			// Registry registry = LocateRegistry.getRegistry(2017);
			// registry.bind("TransactionProcessor", stub);

			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
