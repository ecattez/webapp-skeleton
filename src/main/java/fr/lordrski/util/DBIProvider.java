package fr.lordrski.util;

import org.skife.jdbi.v2.DBI;

/**
 * Allows access to all DAO Classes thanks to a static {@link DBI}
 * 
 * @author Edouard CATTEZ (la7production)
 */
public class DBIProvider {
	
	private static DBI dbi;

	public static DBI getDBI() {
		return dbi;
	}
	
	public static void setDBI(DBI dbi) {
		if (DBIProvider.dbi == null) {
			DBIProvider.dbi = dbi;
		}
	}
	
	public static void setDBI(String uri, String username, String password) {
		setDBI(new DBI(uri, username, password));
	}
	
	public static <SqlObjectType> SqlObjectType getDAO(Class<SqlObjectType> dao) {
		return dbi.onDemand(dao);
	}

}
