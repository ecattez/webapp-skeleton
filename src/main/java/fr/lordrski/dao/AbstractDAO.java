package fr.lordrski.dao;

import org.skife.jdbi.v2.sqlobject.Bind;

public interface AbstractDAO<T> {
	
	public void createTable();
	
	public void clearTable();
	
	public void dropTable();
	
	public void findById(@Bind("id") Integer id);
	
	public void findByName(@Bind("name") String name);
	
	/**
	 * close with no args is used to close the connection
	 */
	public void close();

}
