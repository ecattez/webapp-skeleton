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
package fr.ecattez.dao;

import java.sql.SQLException;
import java.util.List;

import fr.ecattez.entity.deprecated.Company;
import fr.ecattez.entity.deprecated.Group;
import fr.ecattez.entity.deprecated.User;

/**
 * Group Dao qui a pour identifiant un String (Group.groupId).
 */
public interface GroupDao extends AbstractDao<Group, String> {
	
	/**
	 * Charge la liste des groupes de la compagnie passée en paramètre
	 * 
	 * @param companyId la compagnie pour laquelle on veut récupérer les groupes
	 * @return la liste des groupes d'une compagnie
	 * @throws SQLException
	 */
	public List<Group> listGroupsOfCompany(String companyId) throws SQLException;
	
	/**
	 * Charge la liste des groupes de la compagnie passée en paramètre
	 * 
	 * @param company la compagnie pour laquelle on veut récupérer les groupes
	 * @return la liste des groupes d'une compagnie
	 * @throws SQLException
	 */
	public List<Group> listGroupsOf(Company company) throws SQLException;
	
	/**
	 * Charge le groupe associé à l'utilisateur passé en paramètre
	 * 
	 * @param user l'utilisateur pour lequel on veut récupérer le groupe
	 * @return le groupe d'un utilisateur
	 * @throws SQLException
	 */
	public Group getGroupOf(User user) throws SQLException;

}
