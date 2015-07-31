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
package fr.lordrski.dao;

import java.sql.SQLException;

import fr.lordrski.entity.User;

/**
 * User Dao qui a pour un identifiant String (User.login).
 */
public interface UserDao extends AbstractDao<User, String> {
	
	/**
	 * Trouve l'utilisateur via son login et son password
	 * @param login le login de l'utilisateur
	 * @param password le password de l'utilisateur
	 * @return l'utilisateur s'il a été trouvé
	 * @throws SQLException
	 */
	public User findByLogin(String login, String password) throws SQLException;

}
