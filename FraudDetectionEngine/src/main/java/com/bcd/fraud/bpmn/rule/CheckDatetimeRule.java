package com.bcd.fraud.bpmn.rule;

import java.util.Calendar;

import com.bcd.fraud.Transaction;

public class CheckDatetimeRule extends FraudDetectionRule {

	public CheckDatetimeRule(Transaction transaction) {
		super(transaction);
	}

	@Override
	public void execute() {
		if (!isToday(getTransaction().getDateTime())) {
			trigger();
		}
	}

	private boolean isToday(Calendar transactionDate) {
		if (transactionDate == null) {
			return false;
		}

		Calendar today = Calendar.getInstance();

		return (transactionDate.get(Calendar.ERA) == today.get(Calendar.ERA)
				&& transactionDate.get(Calendar.YEAR) == today.get(Calendar.YEAR)
				&& transactionDate.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR));
	}

}
