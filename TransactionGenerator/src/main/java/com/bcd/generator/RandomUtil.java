package com.bcd.generator;

import java.util.Random;

import com.bcd.fraud.Geolocation;
import com.bcd.fraud.Transaction.TransactionType;

public final class RandomUtil {
	
	public enum Location{
		Timisoara(new Geolocation(45.7489, 21.2087)),
		London(new Geolocation(51.5074, -0.1277583)),
		NewYork(new Geolocation(40.7128, 74.0059)),
		Oslo(new Geolocation(59.9139, -10.7522)),
		Bali(new Geolocation(-8.3405, -115.0920));
		
		private final Geolocation geolocation;

		private Location(Geolocation geolocation) {
			this.geolocation = geolocation;
		}

		public Geolocation getGeolocation() {
			return geolocation;
		}
	}
	
	public enum Account{
		Norway1("NO90Nordea80520850294"),
		Norway2("NO57SEB2528527874"),
		Romania1("RO26INGB2894257290000"),
		Romania2("RO45BTRL5892859284222"),
		Germany1("DE3759ALPHA24252232");
		
		private final String account;

		private Account(String account) {
			this.account = account;
		}

		public String getAccount() {
			return account;
		}
		
	}
	private static final Random r = new Random();
	
	private RandomUtil(){
	}

	public static int nextInt(int upperLimit){
		return r.nextInt(upperLimit);
	}
	
	public static boolean nextBoolean(){
		return nextInt(2) == 1;
	}
	
	public static Location randomLocation(){
		Location[] locations = Location.values();
		return locations[nextInt(locations.length)];
	}
	
	public static Account randomAccount(){
		Account[] accounts = Account.values();
		return accounts[nextInt(accounts.length)];
	}
	
	public static TransactionType randomTransactionType(){
		TransactionType[] types = TransactionType.values();
		return types[nextInt(types.length)];
	}
	
	
}
