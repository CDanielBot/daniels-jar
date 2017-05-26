package com.bcd.fraud.bpmn.task;

import com.bcd.fraud.Transaction;
import com.bcd.fraud.Transaction.TransactionState;

public class BlockTransactionTask extends TaskBase {

	@Override
	public void execute() throws Exception {
		Transaction storedTransaction = getStoredTransaction();
		storedTransaction.setState(TransactionState.PotentialFraud);
		transactionDAO.update(storedTransaction);
	}

}
