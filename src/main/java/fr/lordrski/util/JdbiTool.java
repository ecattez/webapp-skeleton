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

import org.skife.jdbi.v2.DBI;

/**
 * Allows access to all DAO Classes thanks to a static {@link DBI}
 */
public class JdbiTool {
	
	private static DBI dbi;

	/**
	 * Donne accès à la base de données via un objet DBI
	 * @return un objet DBI
	 */
	public static DBI getDBI() {
		if (dbi == null) {
			try {
				Class.forName(DBProperties.DB_DRIVER.val());
				dbi = new DBI(DBProperties.DB_URI.val(), DBProperties.DB_USERNAME.val(), DBProperties.DB_PASSWORD.val());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return dbi;
	}
	
	/**
	 * Récupère n'importe quel DAO via l'objet DBI
	 * @param dao le DAO voulu
	 * @return le dao voulu
	 */
	public static <DAO> DAO getDAO(Class<DAO> dao) {
		return getDBI().onDemand(dao);
	}

}
