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
 * Entité représentant un utilisateur
 */
@DatabaseTable
public class User {
	
	@DatabaseField(columnName = "user_id", id = true)
	private UUID userId;
	@DatabaseField(columnName = "auth_id", unique = true)
	private UUID authId;
	@DatabaseField(columnName = "first_name")
	private String firstName;
	@DatabaseField(columnName = "last_name")
	private String lastName;
	@DatabaseField(columnName = "email")
	private String email;
	@DatabaseField(columnName = "phone_number")
	private String phoneNumber;
	@DatabaseField(columnName = "user_meta_id", unique = true)
	private long userMetaId;
	
	public User() {}
	
	public User(UUID userId, UUID authId, String firstName, String lastName, String email, String phoneNumber, long userMetaId) {
		this.userId = userId;
		this.authId = authId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userMetaId = userMetaId;
	}

	/**
	 * @return the userId
	 */
	public UUID getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(UUID userId) {
		this.userId = userId;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the userMetaId
	 */
	public long getUserMetaId() {
		return userMetaId;
	}

	/**
	 * @param userMetaId the userMetaId to set
	 */
	public void setUserMetaId(long userMetaId) {
		this.userMetaId = userMetaId;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof User) && userId.equals(((User) o).userId);
	}
	
	@Override
	public int hashCode() {
		return userId.hashCode();
	}
	
	@Override
	public String toString() {
		return "User[userId=" + userId + ", authId=" + authId + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", userMetaId=" + userMetaId + "]";
	}

}
