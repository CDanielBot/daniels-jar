package com.bcd.fraud.bpmn.rule;

import com.bcd.fraud.Transaction;

public abstract class FraudDetectionRule {
	
	private boolean triggered = false;
	
	private Transaction transaction;
	
	public FraudDetectionRule(Transaction transaction){
		this.transaction = transaction;
	}
	
	public abstract void execute();
	
	public boolean itsBeenTriggered(){
		return triggered;
	}
	
	protected void trigger(){
		triggered = true;
	}
	
	protected Transaction getTransaction(){
		return transaction;
	}

}
