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

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import fr.lordrski.entity.User;
import fr.lordrski.mapper.UserMapper;

/**
 * DAO associé à l'entité {@link fr.lordrski.entity.User}
 */
@RegisterMapper(UserMapper.class)
public interface UserDAO extends DAO<String, User> {
	
	@SqlQuery("select * from users where login = :login and password = :password")
	public User findByLogin(@Bind("login") String pk, @Bind("password") String password);
	
	@Override
	@SqlQuery("select * from users where login = :login")
	public User find(@Bind("login") String pk);
	
	@Override
	@SqlQuery("select * from users")
	public List<User> getAll();
	
	@Override
	@SqlUpdate("insert into users values (:login, :password, :firstname, :lastname, :birthday, :email)")
	public int insert(@BindBean User e);
	
	@Override
	@SqlUpdate("delete from users where login = :login")
	public int delete(@Bind("login") String pk);
	
	@Override
	@SqlUpdate("update users set password = :password, firstname = :firstname, lastname = :lastname, birthday = :birthday, email = :email where login = :login")
	public int update(@BindBean User e);
	
	@Override
	@SqlQuery("select 1 from users where login = :login")
	public boolean exists(@Bind("login") String pk);
	
	@Override
	@SqlQuery("select count(*) from users")
	public int count();

}
