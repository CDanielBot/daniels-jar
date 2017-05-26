package com.bcd.fraud;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1667536912735622874L;

	public enum TransactionType {
		CreditTransfer, Deposit, Withdrawal, OnlinePayment,
	}

	public enum Currency {
		EUR, RON, USD
	}

	public enum TransactionState {
		New, Received, Invalid, PotentialFraud, FraudConfirmed, Success;

		public boolean isBlocked() {
			return this == PotentialFraud || this == FraudConfirmed;
		}

	}

	private String id;
	private TransactionType transactionType;
	private Calendar dateTime;
	private Currency currency;
	private BigDecimal ammount;
	private String accountNumberSource;
	private String accountNumberDestination;
	private Geolocation address;
	private String description;
	private TransactionState state;

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public void setDateTime(Calendar dateTime) {
		this.dateTime = dateTime;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getAmmount() {
		return ammount;
	}

	public void setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
	}

	public String getAccountNumberSource() {
		return accountNumberSource;
	}

	public void setAccountNumberSource(String accountNumberSource) {
		this.accountNumberSource = accountNumberSource;
	}

	public String getAccountNumberDestination() {
		return accountNumberDestination;
	}

	public void setAccountNumberDestination(String accountNumberDestination) {
		this.accountNumberDestination = accountNumberDestination;
	}

	public Geolocation getAddress() {
		return address;
	}

	public void setAddress(Geolocation address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TransactionState getState() {
		return state;
	}

	public void setState(TransactionState state) {
		this.state = state;
	}
	
}
