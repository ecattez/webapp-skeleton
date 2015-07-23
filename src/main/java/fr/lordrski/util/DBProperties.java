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
package fr.lordrski.util;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Charge les propriétés du fichier de configuration.
 * Les propriétés doivent correspondrent à l'énumération.
 */
public enum DBProperties {
	
	DB_DRIVER("db.driver"),
	DB_URI("db.uri"),
	DB_USERNAME("db.username"),
	DB_PASSWORD("db.password");
	
	private static final Properties properties = new Properties();
	private static final Path configPath = Folder.CONFIG.toPath().resolve("config.properties");
	
	static {
		try {
			properties.load(new FileReader(configPath.toFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private final String key;
	
	private DBProperties(final String key) {
		this.key = key;
	}
	
	/**
	 * Retourne la valeur des propriétés associées à l'énumération
	 * 
	 * @return la valeur des propriétés contenues dans le fichier de configuration
	 */
	public final String val() {
		return properties.getProperty(key);
	}

}
