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

import fr.lordrski.dao.UserDao;
import fr.lordrski.entity.Company;
import fr.lordrski.entity.Group;
import fr.lordrski.entity.User;

/**
 * Implémentation JDBC de l'interface UserDao.
 */
public class UserDaoImpl extends AbstractDaoImpl<User, String> implements UserDao {

	public UserDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, User.class);
	}
	
	@Override
	public User findByLogin(String login, String password) throws SQLException {
		QueryBuilder<User, String> queryBuilder = this.queryBuilder();
		queryBuilder.where().idEq(login).and().eq("password", password);
		return this.queryForFirst(queryBuilder.prepare());
	}
	
	@Override
	public List<User> listUsersOfCompany(String companyId) throws SQLException {
		return this.queryForEq("company_id", companyId);
	}

	@Override
	public List<User> listUsersOfGroup(String groupId) throws SQLException {
		return this.queryForEq("group_id", groupId);
	}

	@Override
	public List<User> listUsersOf(Company company) throws SQLException {
		return this.listUsersOfCompany(company.getCompanyId());
	}

	@Override
	public List<User> listUsersOf(Group group) throws SQLException {
		return this.listUsersOfGroup(group.getGroupId());
	}

}
