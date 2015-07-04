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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Loads properties from the config.properties file.
 * Properties in the file should correspond to the enums.
 */
public enum DBProperties {
	
	DB_DRIVER("db.driver"),
	DB_URI("db.uri"),
	DB_USERNAME("db.username"),
	DB_PASSWORD("db.password");
	
	private static final Properties properties = new Properties();
	private static final String filename = "config.properties";
	
	static {
		try {
			properties.load(new FileReader(new File(filename)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private final String key;
	
	private DBProperties(String key) {
		this.key = key;
	}
	
	public String val() {
		return properties.getProperty(key);
	}

}
