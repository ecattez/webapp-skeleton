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
package fr.ecattez.entity.security;

import java.util.Date;
import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.ecattez.dao.impl.SecurIDDaoImpl;

@DatabaseTable(daoClass = SecurIDDaoImpl.class)
public class SecurID {
	
	@DatabaseField(columnName = "sno", id = true)
	private int id;
	@DatabaseField(columnName = "token")
	private UUID token;
	@DatabaseField(columnName = "expiration_date")
	private Date expirationDate;
	
	public SecurID() {}	
	
	public SecurID(int id, UUID token, Date expirationDate) {
		this.id = id;
		this.token = token;
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the token
	 */
	public UUID getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(UUID token) {
		this.token = token;
	}

	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}
