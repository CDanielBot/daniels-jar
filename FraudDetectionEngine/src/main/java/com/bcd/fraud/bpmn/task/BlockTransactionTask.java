package com.bcd.fraud.bpmn.task;

import com.bcd.fraud.Transaction;
import com.bcd.fraud.Transaction.TransactionState;

public class BlockTransactionTask extends TaskBase {

	@Override
	public void execute() throws Exception {
		Transaction storedTransaction = getStoredTransaction();
		System.out.println("Card fraud detected for transaction [id: " + storedTransaction.getId()
				+ "]. Going to reject this transaction!");
		storedTransaction.setState(TransactionState.PotentialFraud);
		transactionDAO.update(storedTransaction);
	}

}
