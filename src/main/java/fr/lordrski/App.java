package fr.lordrski;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import fr.lordrski.mvc.ThymeleafMvcFeature;
import fr.lordrski.util.DBIProvider;
import fr.lordrski.util.DBProvider;
import fr.lordrski.util.ScriptRunner;

/**
 * {@link javax.ws.rs.core.Application} is the resources loader class
 * 
 * @author Edouard CATTEZ (la7production)
 */
public class App extends ResourceConfig {
	
	public App() {
		register(LoggingFilter.class);
		register(ThymeleafMvcFeature.class);
		register(MultiPartFeature.class);
		packages("fr.lordrski.resources");
	}
	
	public App(@Context ServletContext context) {
		this();
		initializeContext(context);
		initializeDB(context);
	}
	
	private void initializeContext(ServletContext context) {
		final String root = context.getContextPath();
		final String real = context.getRealPath(File.separator);
		context.setAttribute("css", root + "/css/");
		context.setAttribute("js", root + "/js/");
		context.setAttribute("exchange", real  + "/exchange/");
	}
	
	private void initializeDB(ServletContext context) {
		Properties prop = new Properties();
		try {
			prop.load(context.getResourceAsStream("/WEB-INF/config.properties"));
			String uri = prop.getProperty("db.uri");
			String username = prop.getProperty("db.username");
			String password = prop.getProperty("db.password");
			DBProvider.initialize(uri, username, password);
			DBIProvider.setDBI(uri, username, password);
			ScriptRunner script = null;
			try (Connection c = DBProvider.getInstance()) {
				script = new ScriptRunner(c, false, true);
				try (Reader reader = new InputStreamReader(context.getResourceAsStream("/WEB-INF/config.sql"))) {
					script.runScript(reader);
				} catch (IOException ie) {
					ie.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
