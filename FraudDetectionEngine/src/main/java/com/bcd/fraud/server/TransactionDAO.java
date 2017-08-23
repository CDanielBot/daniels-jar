package com.bcd.fraud.server;

import java.util.List;

import com.bcd.fraud.Transaction;

public interface TransactionDAO extends DaoBase<Transaction> {
	
	List<Transaction> getAllPossibleFraud();

}
