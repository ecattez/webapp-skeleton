package fr.lordrski.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import fr.lordrski.entity.User;

public class AbstractDAO<T> {
	
	private Class<T> entity;
	private String[] attributes;
	
	public AbstractDAO(Class<T> entity) {
		Field[] fields = entity.getDeclaredFields();
		this.entity = entity;
		this.attributes = new String[fields.length];
		
		for (int i = 0; i < fields.length; i++) {
			attributes[i] = fields[i].getName();
		}
	}
	
	public void create() {}
	
	public void delete(){}
	
	public void insert(){}
	
	public void drop(){}
	
	/**
	 * close with no args is used to close the connection
	 */
	public void close() {}
	
	
	public static void main(String[] args) {
		AbstractDAO<User> a = new AbstractDAO<User>(User.class);
		for (String str : a.attributes)
			System.out.println(str);
	}

}
