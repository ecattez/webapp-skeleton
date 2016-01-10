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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import fr.ecattez.felict.io.IO;
import fr.ecattez.felict.sql.Databases;

/**
 * Offre une représentation objet du fichier de configuration de l'application.
 */
public class AppConfiguration {
	
	private Properties props;
	
	public AppConfiguration(Path config) {
		props = IO.loadProperties(config);
	}
	
	public AppConfiguration(String config) {
		this(Paths.get(config));
	}
	
	/**
	 * @return	toutes les propriétés du fichier de configuration
	 */
	public Properties getProperties() {
		return props;
	}
	
	/**
	 * @return	le package des ressources (web services)
	 */
	public String getResourcePackage() {
		return props.getProperty("resources");
	}
	
	/**
	 * @return	le dossier des scripts SQL
	 */
	public String getSQLFolder() {
		return props.getProperty("sql.folder");
	}
	
	/**
	 * @return	le driver de connexion à la base de données
	 */
	public String getDatabaseDriver() {
		return props.getProperty(Databases.DB_DRIVER);
	}
	
	/**
	 * @return	l'adresse de la base de données
	 */
	public String getDatabaseUri() {
		return props.getProperty(Databases.DB_URI);
	}
	
	/**
	 * @return	l'utilisateur de la base de données
	 */
	public String getDatabaseUserName() {
		return props.getProperty(Databases.DB_USERNAME);
	}
	
	/**
	 * @return	le mot de passe de l'utilisateur de la base de données
	 */
	public String getDatabasePassword() {
		return props.getProperty(Databases.DB_PASSWORD);
	}
	
	/**
	 * Retourne la propriété du fichier de configuration associée à la clé passée en paramètre
	 * 
	 * @param	key
	 * 			la clé de la propriété
	 * 
	 * @return	une propriété du fichier de configuration
	 */
	public String getProperty(String key) {
		return props.getProperty(key);
	}

}
