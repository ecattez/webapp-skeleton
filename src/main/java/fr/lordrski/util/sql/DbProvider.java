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
package fr.lordrski.util.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Donne accès à la base de données via une connexion.
 */
public abstract class DbProvider {
	
	private static Connection connection;
	
	private DbProvider() {}
	
	/**
	 * Donne accès à une connexion à la base de données
	 * 
	 * @return une connexion à la base de données
	 */
	public static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				try {
					Class.forName(DbProperties.DB_DRIVER.val());
					connection = DriverManager.getConnection(
							DbProperties.DB_URI.val(),
							DbProperties.DB_USERNAME.val(),
							DbProperties.DB_PASSWORD.val());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
