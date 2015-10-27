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
package fr.ecattez.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

/**
 * Ajoute de nouvelles méthodes et des méthodes raccourcies à {@link com.j256.ormlite.dao.Dao}.
 */
public abstract class AbstractDaoImpl<T, ID> extends BaseDaoImpl<T, ID> {
	
	public AbstractDaoImpl(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

	/**
	 * {@link #queryForAll}
	 */
	public List<T> findAll() throws SQLException {
		return this.queryForAll();
	}
	
	/**
	 * {@link #queryForId}
	 */
	public T find(ID id) throws SQLException {
		return this.queryForId(id);
	}
	
	/**
	 * {@link #create}
	 */
	public int insert(T data) throws SQLException {
		return this.create(data);
	}

}
