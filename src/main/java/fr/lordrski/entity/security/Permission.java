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
package fr.lordrski.entity.security;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Entité représentant un droit d'accès à une ressource.
 */
@DatabaseTable
public class Permission {
	
	@DatabaseField(columnName = "permission_id")
	private long permissionId;
	@DatabaseField(columnName = "http_uri")
	private String httpUri;
	
	public Permission() {}
	
	public Permission(long permissionId, String httpUri) {
		this.permissionId = permissionId;
		this.httpUri = httpUri;
	}

	/**
	 * @return the permissionId
	 */
	public long getPermissionId() {
		return permissionId;
	}

	/**
	 * @param permissionId the permissionId to set
	 */
	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

	/**
	 * @return the httpUri
	 */
	public String getHttpUri() {
		return httpUri;
	}

	/**
	 * @param httpUri the httpUri to set
	 */
	public void setHttpUri(String httpUri) {
		this.httpUri = httpUri;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Permission) && permissionId == ((Permission)o).permissionId;
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(permissionId);
	}
	
	@Override
	public String toString() {
		return "Permission[permissionId=" + permissionId + ", httpUri=" + httpUri + "]";
	}

}
