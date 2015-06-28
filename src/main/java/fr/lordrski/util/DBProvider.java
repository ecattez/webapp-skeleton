package fr.lordrski.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton giving you a simple connection to database
 * 
 * @author Edouard CATTEZ (la7production)
 */
public class DBProvider {
	
	private static String uri;
	private static String username;
	private static String password;
	
	private static boolean initialized;
	
	private static Connection connect;
	
	public static void initialize(String uri, String username, String password) {
		if (!initialized) {
			DBProvider.uri = uri;
			DBProvider.username = username;
			DBProvider.password = password;
			initialized = true;
		}
	}
	
	public static Connection getInstance() {
		if (connect == null) {
			try {
				connect = DriverManager.getConnection(uri, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connect;
	}

}
