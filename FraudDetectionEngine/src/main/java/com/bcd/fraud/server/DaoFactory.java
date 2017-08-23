package com.bcd.fraud.server;

public final class DaoFactory {

	private DaoFactory(){}
	
	public enum StorageMode{
		IN_MEMORY {
			@Override
			TransactionDAO createNewTransactionDao() {
				return TransactionDaoInMemory.getInstance();
			}
		}, DATABASE {
			@Override
			TransactionDAO createNewTransactionDao() {
				throw new RuntimeException("Database DAOs are not yet supported");
			}
		};
		
		abstract TransactionDAO createNewTransactionDao();
	}
	
	public static final TransactionDAO getTransactionDAO(StorageMode storageMode){
			return storageMode.createNewTransactionDao();
	}
}
