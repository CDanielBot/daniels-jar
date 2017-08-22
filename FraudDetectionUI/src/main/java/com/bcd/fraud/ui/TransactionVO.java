package com.bcd.fraud.ui;

import com.bcd.fraud.Transaction;

import javafx.beans.property.SimpleStringProperty;

@SuppressWarnings("restriction")
public class TransactionVO {

	private final SimpleStringProperty id;
	private final SimpleStringProperty type;
	private final SimpleStringProperty amount;

	
	public TransactionVO(Transaction transaction){
	    	this.id = new SimpleStringProperty(transaction.getId());
	    	this.type = new SimpleStringProperty(transaction.getTransactionType().name());
	    	this.amount = new SimpleStringProperty(convertToAmount(transaction));
	    }

	private final String convertToAmount(Transaction t) {
		String amount = t.getAmmount().toPlainString();
		amount += t.getCurrency() != null ? t.getCurrency().name() : "";
		return amount;
	}

	public String getId(){
		return id.get();
	}
	
	public SimpleStringProperty idProperty() {
		return id;
	}

	public String getType(){
		return type.get();
	}
	
	public SimpleStringProperty typeProperty() {
		return type;
	}
	
	public String getAmount(){
		return amount.get();
	}

	public SimpleStringProperty amountProperty() {
		return amount;
	}
	
	
}
