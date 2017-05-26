package com.bcd.fraud.bpmn.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.bcd.fraud.Transaction;
import com.bcd.fraud.server.DaoFactory;
import com.bcd.fraud.server.TransactionDAO;
import com.bcd.util.Configurator;

public abstract class TaskBase implements JavaDelegate {
	
	protected TransactionDAO transactionDAO = DaoFactory.getTransactionDAO(Configurator.getStorageMode()); 
	protected DelegateExecution execution;
	
	 @Override
    public void execute(DelegateExecution execution) throws Exception {
		 this.beforeExecution(execution);
		 this.execute();
		 this.afterExecution(execution);
	 }
	 
	 public abstract void execute() throws Exception;
	 
	 protected void beforeExecution(DelegateExecution execution){
		 this.execution = execution;
	 }
	 
	 protected void afterExecution(DelegateExecution execution){
	 }
	 
	 protected Transaction getTransaction(){
		 return(Transaction) execution.getVariable("transaction");
	 }
	 
	 protected Transaction getStoredTransaction() {
		 Transaction t = getTransaction();
		 return transactionDAO.get(t.getId());
	 }
}
