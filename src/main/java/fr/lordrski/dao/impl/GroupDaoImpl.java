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
package fr.lordrski.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import fr.lordrski.dao.GroupDao;
import fr.lordrski.entity.Company;
import fr.lordrski.entity.Group;
import fr.lordrski.entity.User;

/**
 * Implémentation JDBC de l'interface GroupDao.
 */
public class GroupDaoImpl extends AbstractDaoImpl<Group, String> implements GroupDao {

	public GroupDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Group.class);
	}

	@Override
	public List<Group> listGroupsOfCompany(String companyId) throws SQLException {
		return this.queryForEq("company_id", companyId);
	}
	
	@Override
	public List<Group> listGroupsOf(Company company) throws SQLException {
		return this.listGroupsOfCompany(company.getCompanyId());
	}

	@Override
	public Group getGroupOf(User user) throws SQLException {
		QueryBuilder<Group, String> qB = this.queryBuilder();
		qB.where().idEq(user.getGroup()).and().eq("company_id", user.getCompany());
		return qB.queryForFirst();
	}

}
