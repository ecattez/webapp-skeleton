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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Charge les propriétés du fichier de configuration.
 * Les propriétés doivent correspondrent à l'énumération.
 */
public enum DbProperties {
	
	DB_DRIVER("db.driver"),
	DB_URI("db.uri"),
	DB_USERNAME("db.username"),
	DB_PASSWORD("db.password");
	
	private static final Properties PROPERTIES = new Properties();
	
	static {
		Path config = Paths.get("config", "config.properties");
		if (Files.exists(config)) {
			try {
				PROPERTIES.load(new FileReader(config.toFile()));
				Logger.getLogger("config").info("Properties in config.properties loaded successfully");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			Logger.getLogger("config").severe("No config.properties found in config folder");
		}
	}
	
	private final String key;
	
	private DbProperties(final String key) {
		this.key = key;
	}
	
	/**
	 * Retourne la valeur des propriétés associées à l'énumération
	 * 
	 * @return la valeur des propriétés contenues dans le fichier de configuration
	 */
	public final String val() {
		return PROPERTIES.getProperty(key);
	}

}
