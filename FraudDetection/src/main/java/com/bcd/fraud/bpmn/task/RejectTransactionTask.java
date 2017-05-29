package com.bcd.fraud.bpmn.task;

import com.bcd.fraud.Transaction;
import com.bcd.fraud.Transaction.TransactionState;

public class RejectTransactionTask extends TaskBase {

	@Override
	public void execute() throws Exception {
		Transaction storedTransaction = getStoredTransaction();
		storedTransaction.setState(TransactionState.Invalid);
		transactionDAO.update(storedTransaction);
	}

}
