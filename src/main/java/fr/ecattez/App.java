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
package fr.ecattez;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import fr.ecattez.mvc.security.AuthenticationFilter;
import fr.ecattez.mvc.standard.CORSResponseFilter;
import fr.ecattez.mvc.standard.ThymeleafMvcFeature;
import fr.ecattez.util.sql.ScriptRunner;

/**
 * {@link javax.ws.rs.core.Application} charge toutes les ressources de l'application.
 */
public class App extends ResourceConfig {
	
	public App() {
		ScriptRunner.runDefaultScripts();
		packages("fr.ecattez.resource");
		register(LoggingFilter.class);
		register(AuthenticationFilter.class);
		register(CORSResponseFilter.class);
		register(ThymeleafMvcFeature.class);
		register(MultiPartFeature.class);
	}

}
