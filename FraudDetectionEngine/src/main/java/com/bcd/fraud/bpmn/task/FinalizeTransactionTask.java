package com.bcd.fraud.bpmn.task;

import com.bcd.fraud.Transaction;
import com.bcd.fraud.Transaction.TransactionState;

public class FinalizeTransactionTask extends TaskBase {

	@Override
	public void execute() throws Exception {
		Transaction storedTransaction = getStoredTransaction();
		System.out.println("Successfully checked transaction [id: " + storedTransaction.getId() + "] for card fraud. No fraud detected!");
		storedTransaction.setState(TransactionState.Success);
		transactionDAO.update(storedTransaction);
	}

}
