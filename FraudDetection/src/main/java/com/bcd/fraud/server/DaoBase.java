package com.bcd.fraud.server;

import java.util.List;

public interface DaoBase<T> {

	public T create(T t);
	
	public T update(T t);
	
	public T get(String id);
	
	public List<T> getAll();
	
	public boolean delete(T t);
}
