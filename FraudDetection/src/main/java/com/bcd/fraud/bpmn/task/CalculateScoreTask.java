package com.bcd.fraud.bpmn.task;

import java.math.BigDecimal;
import java.util.Calendar;

import com.bcd.fraud.Geolocation;
import com.bcd.fraud.Transaction;

public class CalculateScoreTask extends TaskBase {

	@Override
	public void execute() throws Exception {
		int score = 0;

		Transaction transaction = getStoredTransaction();
		System.out.println("Calculating score for transaction: " + transaction);

		Geolocation address = transaction.getAddress();
		if (address.getLatitude() > 50 || address.getLongitude() < 0) {
			score += Math.abs(address.getLatitude()) + Math.abs(address.getLongitude());
		}

		String destination = transaction.getAccountNumberDestination();
		if (destination != null && !destination.startsWith("RO")) {
			score += 250;
		}

		if (transaction.getDateTime().compareTo(Calendar.getInstance()) < 0) {
			score += 200;
		}
		
		if(transaction.getAmmount().compareTo(new BigDecimal(100_000)) < 0) {
			score += 300;
		}

		transaction.setScore(score);
		transactionDAO.update(transaction);

	}
}
