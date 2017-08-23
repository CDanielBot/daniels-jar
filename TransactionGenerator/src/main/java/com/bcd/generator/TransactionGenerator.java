package com.bcd.generator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.bcd.fraud.Geolocation;
import com.bcd.fraud.Transaction;
import com.bcd.fraud.Transaction.Currency;

public class TransactionGenerator {

	public enum TransactionType {
		Valid {
			@Override
			public void prepareAccordingly(Transaction t) {
				t.setCurrency(Currency.RON);
			}
		},
		BigAmount {
			@Override
			public void prepareAccordingly(Transaction t) {
				t.setAmmount(new BigDecimal(100_000_000));
				t.setTransactionType(Transaction.TransactionType.Withdrawal);
			}
		},
		BadLocation {
			@Override
			public void prepareAccordingly(Transaction t) {
				t.setAddress(new Geolocation(35.360638, 138.729050));// mount Fuji
			}
		},
		UnusualDateTime {
			@Override
			public void prepareAccordingly(Transaction t) {
				Calendar c = Calendar.getInstance();
				c.roll(Calendar.MONTH, RandomUtil.nextBoolean() ? 1 : -1);
				t.setDateTime(c);
			}
		};

		public abstract void prepareAccordingly(Transaction t);

	}

	public enum TypeCategory {
		Valid {
			@Override
			public TransactionType random() {
				return TransactionType.Valid;
			}
		},
		SuspectOfFraud {
			@Override
			public TransactionType random() {
				return suspectLookup.get(new Random().nextInt(suspectLookup.size()));
			}
		};

		public abstract TransactionType random();

		private static final List<TransactionType> suspectLookup = Arrays.asList(TransactionType.UnusualDateTime,
				TransactionType.BadLocation, TransactionType.BigAmount);
	}

	public static Transaction generate(TransactionType type) {

		Transaction t = new Transaction();

		t.setId(UUID.randomUUID().toString());
		t.setDateTime(Calendar.getInstance());
		t.setAddress(RandomUtil.randomLocation().getGeolocation());
		t.setAccountNumberSource(RandomUtil.randomAccount().getAccount());
		t.setAmmount(new BigDecimal(RandomUtil.nextInt(500_000)));
		t.setTransactionType(RandomUtil.randomTransactionType());

		type.prepareAccordingly(t);

		return t;
	}
}
