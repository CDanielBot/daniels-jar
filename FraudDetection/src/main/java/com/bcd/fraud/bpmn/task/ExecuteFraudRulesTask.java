package com.bcd.fraud.bpmn.task;

import java.util.List;

import com.bcd.fraud.Transaction;
import com.bcd.fraud.bpmn.ProcessVariables;
import com.bcd.fraud.bpmn.rule.FraudDetectionRule;
import com.bcd.fraud.bpmn.rule.RulesFactory;

public class ExecuteFraudRulesTask extends TaskBase {

	@Override
	public void execute() throws Exception {

		System.out.println("Executing fraud rules for transaction with id: " + getTransaction().getId());
		boolean isAnyRuleTriggered = executeRules();
		setVariable(ProcessVariables.IS_RULE_TRIGGERED, isAnyRuleTriggered);
	}

	private boolean executeRules() {

		Transaction t = getStoredTransaction();

		List<FraudDetectionRule> rules = RulesFactory.getRules(t);
		for (FraudDetectionRule rule : rules) {
			rule.execute();
			if (rule.itsBeenTriggered()) {
				return true;
			}
		}

		return false;
	}

}
