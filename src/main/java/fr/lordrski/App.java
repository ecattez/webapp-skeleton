package fr.lordrski;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.skife.jdbi.v2.DBI;

import fr.lordrski.dao.UserDAO;
import fr.lordrski.resources.HelloResource;
import fr.lordrski.resources.MainResource;
import fr.lordrski.resources.UserResource;
import fr.lordrski.util.Configuration;

@ApplicationPath("/*")
public class App extends Application {
	
	private static DBI dbi;
	
	public static DBI getDBI() {
		return dbi;
	}
	
	static {
		Configuration config = new Configuration();
		config.load();
		dbi = new DBI(config.getProperty("driver") + ":" + config.getProperty("database"));
		UserDAO userDao = App.dbi.open(UserDAO.class);
		userDao.createTable();
		userDao.close();
	}
	
	public App() {}
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(LoggingFilter.class);
		s.add(MainResource.class);
		s.add(UserResource.class);
		s.add(HelloResource.class);
		return s;
	}

}
