package com.bcd.fraud.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.bcd.fraud.Transaction;
import com.bcd.fraud.Transaction.TransactionState;

public class TransactionDaoInMemory implements TransactionDAO {

	private Map<String, Transaction> inMemoryStorage = new ConcurrentHashMap<>();

	@Override
	public Transaction create(Transaction t) {
		return inMemoryStorage.put(t.getId(), t);
	}

	@Override
	public Transaction update(Transaction t) {
		return inMemoryStorage.put(t.getId(), t);
	}

	@Override
	public Transaction get(String id) {
		return inMemoryStorage.get(id);
	}

	@Override
	public List<Transaction> getAll() {
		return new ArrayList<>(inMemoryStorage.values());
	}

	@Override
	public boolean delete(Transaction t) {
		return inMemoryStorage.remove(t.getId()) != null;
	}

	@Override
	public List<Transaction> getAllPossibleFraud() {
		
		List<Transaction> result = new ArrayList<>();
		
		for(Transaction t : inMemoryStorage.values()){
			if(t.getState() == TransactionState.PotentialFraud){
				result.add(t);
			}
		}
		
		return result;
	}

}
