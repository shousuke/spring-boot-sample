package entpay.dao;

import java.util.List;

import entpay.exception.DataAccessException;

public interface BaseDao<T> {

	public void insert(T model) throws DataAccessException;

	public T getById(int id);
	
	public T getBy(String[] columnNames, Object[] columnValues);

	public void update(T model) throws DataAccessException;

	public int deleteById(int id);

	public List<T> getAll();
	
	public int deleteAll();
}
