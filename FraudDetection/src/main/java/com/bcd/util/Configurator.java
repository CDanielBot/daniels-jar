package com.bcd.util;

import com.bcd.fraud.server.DaoFactory.StorageMode;

public class Configurator {
	
	public static StorageMode getStorageMode(){
		return StorageMode.IN_MEMORY;
	}

}
