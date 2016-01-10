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

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import fr.ecattez.felict.sql.Databases;
import fr.ecattez.felict.sql.Scripts;
import fr.ecattez.mvc.security.AuthenticationFilter;
import fr.ecattez.mvc.standard.CORSResponseFilter;
import fr.ecattez.mvc.standard.ThymeleafMvcFeature;

/**
 * {@link javax.ws.rs.core.Application} charge toutes les ressources de l'application.
 */
public class App extends ResourceConfig {
	
	public final static Path CONFIGURATION = Paths.get("config", "config.properties");
	
	private static AppConfiguration configProperties;
	
	public App() {
		AppConfiguration config = getConfigProperties();
		String sqlFolder = config.getSQLFolder();
		String resourcePackage = config.getResourcePackage();
		Connection con = Databases.connect(config.getProperties());
		
		this.runScripts(con, sqlFolder);
		this.packages(resourcePackage);
		this.registerAll();
	}
	
	/**
	 * Retourne les propriétés de configuration de l'application
	 * 
	 * @return	la configuration de l'application sous forme de clés/valeurs
	 */
	public static AppConfiguration getConfigProperties() {
		if (configProperties == null) {
			configProperties = new AppConfiguration(CONFIGURATION);
		}
		return configProperties;
	}
	
	/**
	 * Exécute tous les scripts SQL
	 * 
	 * @param	con
	 * 			la connexion à la base de données
	 * @param	sqlFolder
	 * 			le dossier contenant les scripts SQL
	 */
	private void runScripts(Connection con, String sqlFolder) {
		try {
			Scripts.runScript(con, Paths.get(sqlFolder));
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Enregistre les classes nécessaires au bon fonctionnement de l'application
	 */
	private void registerAll() {
		register(LoggingFilter.class);
		register(AuthenticationFilter.class);
		register(CORSResponseFilter.class);
		register(ThymeleafMvcFeature.class);
		register(MultiPartFeature.class);
	}

}
