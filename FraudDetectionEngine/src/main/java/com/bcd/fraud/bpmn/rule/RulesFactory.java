package com.bcd.fraud.bpmn.rule;

import java.util.ArrayList;
import java.util.List;

import com.bcd.fraud.Transaction;

public class RulesFactory {

	public static final List<FraudDetectionRule> getRules(Transaction t){
		List<FraudDetectionRule> rules = new ArrayList<>();
		
		rules.add(new CheckAmountRule(t));
		rules.add(new CheckLocationRule(t));
		rules.add(new CheckDatetimeRule(t));
		
		return rules;
	}
}
