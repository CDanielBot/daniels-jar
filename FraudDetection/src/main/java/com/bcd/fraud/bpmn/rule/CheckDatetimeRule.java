package com.bcd.fraud.bpmn.rule;

import com.bcd.fraud.Transaction;

public class CheckDatetimeRule extends FraudDetectionRule{

	public CheckDatetimeRule(Transaction transaction) {
		super(transaction);
	}

	@Override
	public void execute() {
		
	}
}
