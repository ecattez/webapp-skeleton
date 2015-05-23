package fr.lordrski.util;

import org.skife.jdbi.v2.DBI;

/**
 * Allows access to all DAO Classes thanks to a static {@link DBI}
 * 
 * @author Edouard CATTEZ (la7production)
 */
public class DBIProvider {
	
	private static DBI dbi;
	
	public static void initializeDefault() {
		if (dbi == null) {
			PropertyFile config = new PropertyFile("config.properties", true);
			dbi = new DBI(config.getProperty("driver") + ":" + config.getProperty("database"));
		}
	}

	public static DBI getDBI() {
		return dbi;
	}
	
	public static void setDBI(DBI _dbi) {
		if (dbi == null) {
			dbi = _dbi;
		}
	}
	
	public static <SqlObjectType> SqlObjectType getDAO(Class<SqlObjectType> dao) {
		return dbi.onDemand(dao);
	}

}
