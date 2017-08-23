package com.bcd.fraud.bpmn.rule;

import java.math.BigDecimal;

import com.bcd.fraud.Transaction;
import com.bcd.fraud.Transaction.TransactionType;

public class CheckAmountRule extends FraudDetectionRule{

	public CheckAmountRule(Transaction transaction) {
		super(transaction);
	}

	@Override
	public void execute() {
		Transaction transaction = getTransaction();
		if(transaction.getTransactionType() == TransactionType.Withdrawal &&
				transaction.getAmmount().compareTo(new BigDecimal(99_999_999)) > 0){
			trigger();
		}
	}

}
