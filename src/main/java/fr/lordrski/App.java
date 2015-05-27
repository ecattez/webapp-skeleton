package fr.lordrski;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import fr.lordrski.mvc.ThymeleafMvcFeature;
import fr.lordrski.util.DBIProvider;

/**
 * {@link javax.ws.rs.core.Application} is the resources loader class
 * 
 * @author Edouard CATTEZ (la7production)
 */
public class App extends ResourceConfig {
	
	public App() {
		DBIProvider.initializeDefault();
		register(LoggingFilter.class);
		register(ThymeleafMvcFeature.class);
		register(MultiPartFeature.class);
		packages("fr.lordrski.resources");
	}
	
	public App(@Context ServletContext context) {
		this();
		initialize(context);
	}
	
	private void initialize(ServletContext context) {
		final String root = context.getContextPath();
		context.setAttribute("css", root + "/css/");
		context.setAttribute("js", root + "/js/");
	}

}
