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

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import fr.lordrski.mvc.CORSResponseFilter;
import fr.lordrski.mvc.ThymeleafMvcFeature;
import fr.lordrski.util.ScriptRunner;

/**
 * {@link javax.ws.rs.core.Application} charge toutes les ressources de l'application.
 */
public class App extends ResourceConfig {
	
	public App() {
		ScriptRunner.runDefaultScripts();
		packages("fr.lordrski.resource");
		register(LoggingFilter.class);
		register(MultiPartFeature.class);
		register(CORSResponseFilter.class);
		register(ThymeleafMvcFeature.class);
	}
	
	public App(@Context ServletContext context) {
		this();
		final String gui = "/gui";
		context.setAttribute("css", gui + "/themes/");
		context.setAttribute("default_css", gui + "/themes/style.css");
		context.setAttribute("js", gui + "/scripts/");
		context.setAttribute("jQuery", gui + "/scripts/jquery-2.1.4.min.js");
	}

}
