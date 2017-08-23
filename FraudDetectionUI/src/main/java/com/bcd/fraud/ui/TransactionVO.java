package com.bcd.fraud.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.bcd.fraud.Transaction;

import javafx.beans.property.SimpleStringProperty;

@SuppressWarnings("restriction")
public class TransactionVO {

	private final SimpleStringProperty id;
	private final SimpleStringProperty type;
	private final SimpleStringProperty amount;
	private final SimpleStringProperty dateTime;
	private final SimpleStringProperty accountNumber;

	public TransactionVO(Transaction transaction) {
		this.id = new SimpleStringProperty(Converter.toSensibleData(transaction.getId()));
		this.type = new SimpleStringProperty(transaction.getTransactionType().name());
		this.amount = new SimpleStringProperty(Converter.toAmount(transaction));
		this.dateTime = new SimpleStringProperty(Converter.toDateTime(transaction.getDateTime()));
		this.accountNumber = new SimpleStringProperty(transaction.getAccountNumberSource());
	}

	public String getId() {
		return id.get();
	}

	public SimpleStringProperty idProperty() {
		return id;
	}

	public String getType() {
		return type.get();
	}

	public SimpleStringProperty typeProperty() {
		return type;
	}

	public String getAmount() {
		return amount.get();
	}

	public SimpleStringProperty amountProperty() {
		return amount;
	}

	public String getDateTime() {
		return dateTime.get();
	}

	public SimpleStringProperty dateTimeProperty() {
		return dateTime;
	}

	public String getAccountNumber() {
		return accountNumber.get();
	}

	public SimpleStringProperty accountNumberProperty() {
		return accountNumber;
	}

	private static final class Converter {

		private static final DateFormat dateTimeFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		static final String toAmount(Transaction t) {
			String amount = t.getAmmount().toPlainString();
			amount += t.getCurrency() != null ? t.getCurrency().name() : "";
			return amount;
		}

		static final String toSensibleData(String data) {
			return "****" + data.substring(data.length() - 5);
		}

		static final String toDateTime(Calendar dateTime) {
			return dateTimeFormater.format(dateTime.getTime());
		}
	}

}
