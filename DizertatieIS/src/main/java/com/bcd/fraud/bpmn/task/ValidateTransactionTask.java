package com.bcd.fraud.bpmn.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.bcd.fraud.Transaction;

public class ValidateTransactionTask implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		//TODO validate transaction
		Transaction transaction = (Transaction) execution.getVariable("transaction");
		System.out.println("Validating transaction with id: " + transaction.getId());
	}

}
