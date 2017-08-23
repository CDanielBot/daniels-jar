package com.bcd.fraud.bpmn.rule;

import com.bcd.fraud.Geolocation;
import com.bcd.fraud.Transaction;

public class CheckLocationRule extends FraudDetectionRule{

	public CheckLocationRule(Transaction transaction) {
		super(transaction);
	}

	@Override
	public void execute() {
		
		Geolocation address = getTransaction().getAddress();
		if(address.getLatitude() > 35 && address.getLatitude() < 40 &&
				address.getLongitude() > 130 && address.getLongitude() < 140){
			trigger();
		}
	}
}
