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

/**
 * Entité représentant une clé de sécurité.
 */
@DatabaseTable(daoClass = SecurIDDaoImpl.class)
public class SecurID {
	
	@DatabaseField(columnName = "auth_id", id = true, uniqueCombo = true)
	private UUID authId;
	@DatabaseField(columnName = "token_id", uniqueCombo = true)
	private UUID tokenId;
	@DatabaseField(columnName = "expiration_date", canBeNull = false)
	private Date expirationDate;
	
	public SecurID() {}
	
	public SecurID(UUID authId, UUID tokenId, Date expirationDate) {
		this.authId = authId;
		this.tokenId = tokenId;
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the authId
	 */
	public UUID getAuthId() {
		return authId;
	}
	
	/**
	 * @param authId the authId to set
	 */
	public void setAuthId(UUID authId) {
		this.authId = authId;
	}
	
	/**
	 * @return the tokenId
	 */
	public UUID getTokenId() {
		return tokenId;
	}
	
	/**
	 * @param tokenId the tokenId to set
	 */
	public void setTokenId(UUID tokenId) {
		this.tokenId = tokenId;
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
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof SecurID) && authId.equals(((SecurID)o).authId);
	}
	
	@Override
	public int hashCode() {
		return authId.hashCode();
	}
	
	@Override
	public String toString() {
		return "SecurID[authId=" + authId + ", tokenId=" + tokenId + ", expirationDate=" + expirationDate + "]";
	}

}
