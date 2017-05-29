package com.bcd.fraud.bpmn.task;

import com.bcd.fraud.Transaction;
import com.bcd.fraud.Transaction.TransactionState;

public class FinalizeTransactionTask extends TaskBase {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		Transaction storedTransaction = getStoredTransaction();
		storedTransaction.setState(TransactionState.Success);
		transactionDAO.update(storedTransaction);
	}

}
