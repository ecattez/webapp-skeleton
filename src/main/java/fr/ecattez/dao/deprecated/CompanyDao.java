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

import fr.ecattez.entity.deprecated.Company;
import fr.ecattez.entity.deprecated.Group;
import fr.ecattez.entity.deprecated.User;

/**
 * Company Dao qui a pour identifiant un String (Company.companyId).
 */
public interface CompanyDao extends AbstractDao<Company, String> {
	
	/**
	 * Charge les informations de la compagnie associée au groupe passé en paramètre
	 * 
	 * @param group le groupe pour lequel on veut retrouver la compagnie
	 * @return la compagnie d'un groupe
	 * @throws SQLException
	 */
	public Company getCompanyOf(Group group) throws SQLException;
	
	/**
	 * Charge les information de la compagnie associée à l'utilisateur passé en paramètre
	 * 
	 * @param user l'utilisateur pour lequel on veut retrouver la compagnie
	 * @return la compagnie d'un l'utilisateur
	 * @throws SQLException
	 */
	public Company getCompanyOf(User user) throws SQLException;

}
