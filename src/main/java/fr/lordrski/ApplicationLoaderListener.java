package fr.lordrski;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationLoaderListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		ServletContext context = e.getServletContext();
		final String root = context.getContextPath();
		context.setAttribute("root", root);
		context.setAttribute("css", root + "/css");
		context.setAttribute("js", root + "/js");		
	}

}
