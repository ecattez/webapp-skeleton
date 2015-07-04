/**
 * This file is part of webapp-skeleton.
 *
 * webapp-skeleton is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * webapp-skeleton is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.				 
 * 
 * You should have received a copy of the GNU General Public License
 * along with webapp-skeleton.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Edouard CATTEZ <edouard.cattez@sfr.fr> (La 7 Production)
 */
package fr.lordrski;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import fr.lordrski.mvc.ThymeleafMvcFeature;
import fr.lordrski.util.JdbiTool;
import fr.lordrski.util.ScriptRunner;

/**
 * {@link javax.ws.rs.core.Application} is the resources loader class.
 */
public class App extends ResourceConfig {
	
	private static boolean initialized = false;
	
	public App() {
		register(LoggingFilter.class);
		register(ThymeleafMvcFeature.class);
		register(MultiPartFeature.class);
		packages("fr.lordrski.resources");
		initializeScript();
	}
	
	public App(@Context ServletContext context) {
		this();
		initializeContext(context);
	}
	
	private void initializeContext(ServletContext context) {
		final String root = context.getContextPath();
		final String real = context.getRealPath(File.separator);
		context.setAttribute("css", root + "/css/");
		context.setAttribute("default_css", root + "/css/style.css");
		context.setAttribute("js", root + "/js/");
		context.setAttribute("jQuery", root + "/js/jquery-2.1.4.min.js");
		context.setAttribute("exchange", real  + "/exchange/");
	}
	
	private void initializeScript() {
		if (!initialized) {
			ScriptRunner script = new ScriptRunner(JdbiTool.getDBI().open().getConnection(), false ,false);
			try {
				script.runScript(new FileReader(new File("config.sql")));
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
			initialized = true;
		}
	}

}
