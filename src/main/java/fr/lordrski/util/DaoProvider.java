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

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;

/**
 * Permet l'accès à la base de données et le DAO.
 */
public abstract class DaoProvider {
	
	private static ConnectionSource conSource;
	
	/**
	 * Empêche l'instanciation de la classe.
	 */
	private DaoProvider() {}

	/**
	 * Donne accès à la base de données via une source de connexion
	 * 
	 * @return une source de connexion
	 */
	public static ConnectionSource getConnectionSource() {
		if (conSource == null) {
			try {
				Class.forName(DbProperties.DB_DRIVER.val());
				conSource = new JdbcConnectionSource(DbProperties.DB_URI.val(), DbProperties.DB_USERNAME.val(), DbProperties.DB_PASSWORD.val());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return conSource;
	}
	
	/**
	 * Donne accès en lecture et en écriture à la base de données
	 * 
	 * @return une connexion à la base
	 */
	public static DatabaseConnection getConnection() {
		try {
			return getConnectionSource().getReadWriteConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Récupère n'importe quel Dao
	 * 
	 * @param daoObject l'objet associé à une table en base
	 * @return le dao voulu
	 */
	public static synchronized <D extends Dao<T, ?>, T> D getDao(Class<T> daoObject) {
		try {
			D dao = DaoManager.createDao(getConnectionSource(), daoObject);
			return dao;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
