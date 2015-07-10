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
package fr.lordrski.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import fr.lordrski.entity.User;

/**
 * Une classe de mapping JDBI pour les instances de {@link fr.lordrski.entity.User}.
 */
public class UserMapper implements ResultSetMapper<User> {

	@Override
	public User map(int index, ResultSet rs, StatementContext sc) throws SQLException {
		return new User(rs.getString("login"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("birthday"), rs.getString("email"));
	}

}
