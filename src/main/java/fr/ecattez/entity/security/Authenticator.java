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

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Entité représentant l'authentificateur d'un utilisateur.
 */
@DatabaseTable
public class Authenticator {
	
	@DatabaseField(columnName = "auth_id", id = true)
	private UUID authId;
	@DatabaseField(columnName = "username")
	private String username;
	@DatabaseField(columnName = "password_salt")
	private String passwordSalt;
	@DatabaseField(columnName = "password_hash")
	private String passwordHash;
	
	public Authenticator() {}
	
	public Authenticator(UUID authId, String username, String passwordSalt, String passwordHash) {
		this.authId = authId;
		this.username = username;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the passwordSalt
	 */
	public String getPasswordSalt() {
		return passwordSalt;
	}

	/**
	 * @param passwordSalt the passwordSalt to set
	 */
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	/**
	 * @return the passwordHash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * @param passwordHash the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Authenticator) && authId.equals(((Authenticator)o).authId);
	}
	
	@Override
	public int hashCode() {
		return authId.hashCode();
	}
	
	@Override
	public String toString() {
		return "Authenticator[authId=" + authId + ", username=" + username
					+ ", passwordSalt=" + passwordSalt + ", passwordHash=" + passwordHash + "]";
	}

}
