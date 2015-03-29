package fr.lordrski;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.skife.jdbi.v2.DBI;

@ApplicationPath("/")
public class App extends Application {
	
	public static String DEFAULT_DRIVER = "jdbc:sqlite:";
	public static DBI dbi = new DBI(DEFAULT_DRIVER + "data.db");
	
	static {
		UserDAO userDao = App.dbi.open(UserDAO.class);
		userDao.createTable();
		userDao.close();
	}
	
	public App() {}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(LoggingFilter.class);
		s.add(UserResource.class);
		s.add(UserDBResource.class);
		return s;
	}

}
