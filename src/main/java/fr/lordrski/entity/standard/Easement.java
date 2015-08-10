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
package fr.lordrski.entity.standard;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Entité représentant un droit d'accès (association entre une compagnie, un groupe et une permission).
 */
@DatabaseTable
public class Easement {
	
	@DatabaseField
	private long easementId;
	@DatabaseField
	private UUID companyId;
	@DatabaseField
	private UUID groupId;
	@DatabaseField
	private long permissionId;
	
	public Easement() {}
	
	public Easement(long easementId, UUID companyId, UUID groupId, long permissionId) {
		this.companyId = companyId;
		this.groupId = groupId;
		this.permissionId = permissionId;
	}

	/**
	 * @return the easementId
	 */
	public long getEasementId() {
		return easementId;
	}

	/**
	 * @param easementId the easementId to set
	 */
	public void setEasementId(long easementId) {
		this.easementId = easementId;
	}

	/**
	 * @return the companyId
	 */
	public UUID getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(UUID companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the groupId
	 */
	public UUID getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(UUID groupId) {
		this.groupId = groupId;
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

	@Override
	public boolean equals(Object o) {
		return (o instanceof Easement) && easementId == ((Easement)o).easementId;
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(easementId);
	}
	
	@Override
	public String toString() {
		return "Easement[easementId=" + easementId + ", companyId=" + companyId
					+ ", groupId=" + groupId + ", permissionId=" + permissionId + "]";
	}
	
}
