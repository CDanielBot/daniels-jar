package com.bcd.fraud.bpmn.rule;

import com.bcd.fraud.Transaction;

public class CheckLocationRule extends FraudDetectionRule{

	public CheckLocationRule(Transaction transaction) {
		super(transaction);
	}

	@Override
	public void execute() {
		
	}
}
