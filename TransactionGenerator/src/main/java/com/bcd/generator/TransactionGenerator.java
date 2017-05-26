package com.bcd.generator;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

import com.bcd.fraud.Geolocation;
import com.bcd.fraud.Transaction;
import com.bcd.fraud.Transaction.Currency;

public class TransactionGenerator {

	public enum TransactionType {
		Valid(TypeCategory.Valid) {
			@Override
			public void prepareAccordingly(Transaction t) {
				t.setCurrency(Currency.RON);
			}
		},
		BigAmount(TypeCategory.Fraud) {
			@Override
			public void prepareAccordingly(Transaction t) {
				t.setAmmount(new BigDecimal(100_000_000));
				t.setTransactionType(Transaction.TransactionType.Withdrawal);
			}
		},
		BadLocation(TypeCategory.Fraud) {
			@Override
			public void prepareAccordingly(Transaction t) {
				t.setAddress(new Geolocation(35.360638, 138.729050));//mount Fuji
			}
		},
		UnusualDateTime(TypeCategory.Suspect) {
			@Override
			public void prepareAccordingly(Transaction t) {
				Calendar c = Calendar.getInstance();
				c.roll(Calendar.MONTH, RandomUtil.nextBoolean() ? 1 : -1);
				t.setDateTime(c);
			}
		};

		private final TypeCategory category;

		private static final TransactionType[] suspectLookup = new TransactionType[1];
		private static final TransactionType[] fraudLookup = new TransactionType[2];

		static {
			suspectLookup[0] = TransactionType.UnusualDateTime;

			fraudLookup[0] = TransactionType.BadLocation;
			fraudLookup[1] = TransactionType.BigAmount;
		}

		private TransactionType(TypeCategory category) {
			this.category = category;
		}

		public abstract void prepareAccordingly(Transaction t);

		public static TransactionType random(TypeCategory category) {
			if (TypeCategory.Valid == category) {
				return Valid;
			} else if (TypeCategory.Suspect == category) {
				int random = new Random().nextInt(suspectLookup.length);
				return suspectLookup[random];
			} else {
				int random = new Random().nextInt(fraudLookup.length);
				return fraudLookup[random];
			}
		}

	}

	public enum TypeCategory {
		Valid, Suspect, Fraud;
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
