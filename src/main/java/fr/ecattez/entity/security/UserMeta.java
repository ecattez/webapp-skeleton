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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Entité représentant les informations complémentaires d'un utilisateur.
 */
@DatabaseTable
public class UserMeta {
	
	@DatabaseField(columnName = "user_meta_id", id = true)
	private long userMetaId;
	
	public UserMeta() {}
	
	public UserMeta(long userMetaId) {
		this.userMetaId = userMetaId;
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
		return (o instanceof UserMeta) && userMetaId == ((UserMeta)o).userMetaId;
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(userMetaId);
	}
	
	@Override
	public String toString() {
		return "UserMeta[userMetaId=" + userMetaId + "]";
	}

}
