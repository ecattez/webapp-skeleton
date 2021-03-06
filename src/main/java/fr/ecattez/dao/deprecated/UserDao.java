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
package fr.ecattez.dao.deprecated;

import java.sql.SQLException;
import java.util.List;

import fr.ecattez.entity.deprecated.Company;
import fr.ecattez.entity.deprecated.Group;
import fr.ecattez.entity.deprecated.User;

/**
 * User Dao qui a pour identifiant un String (User.login).
 */
public interface UserDao extends AbstractDao<User, String> {
	
	/**
	 * Trouve l'utilisateur via son login et son password
	 * 
	 * @param login le login de l'utilisateur
	 * @param password le password de l'utilisateur
	 * @return l'utilisateur s'il a été trouvé
	 * @throws SQLException
	 */
	public User findByLogin(String login, String password) throws SQLException;
	
	/**
	 * Charge la liste des utilisateurs de la compagnie passée en paramètre
	 * 
	 * @param companyId la compagnie pour laquelle on veut retrouver les utilisateurs
	 * @return la liste des utilisateurs d'une compagnie
	 * @throws SQLException
	 */
	public List<User> listUsersOfCompany(String companyId) throws SQLException;
	
	/**
	 * Charge la liste des utilisateurs du groupe passé en paramètre
	 * 
	 * @param companyId la compagnie du groupe pour lequel on veut retrouver les utilisateurs
	 * @param groupId le groupe pour lequel on veut retrouver les utilisateurs
	 * @return la liste des utilisateurs d'un groupe
	 * @throws SQLException
	 */
	public List<User> listUsersOfGroup(String companyId, String groupId) throws SQLException;
	
	/**
	 * Charge la liste des utilisateurs de la compagnie passée en paramètre
	 * 
	 * @param company la compagnie pour laquelle on veut retrouver les utilisateurs
	 * @return la liste des utilisateurs d'une compagnie
	 * @throws SQLException
	 */
	public List<User> listUsersOf(Company company) throws SQLException;
	
	/**
	 * Charge la liste des utilisateurs du groupe passé en paramètre
	 * 
	 * @param group le groupe pour lequel on veut retrouver les utilisateurs
	 * @return la liste des utilisateurs d'un groupe
	 * @throws SQLException
	 */
	public List<User> listUsersOf(Group group) throws SQLException;

}
