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

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Entité représentant un groupe associable à des utilisateurs.
 */
@DatabaseTable
public class Group {

	@DatabaseField(columnName = "groupId", id = true)
	private UUID groupId;
	@DatabaseField(columnName = "groupName", unique = true)
	private String groupName;
	
	public Group() {}
	
	public Group(UUID groupId, String groupName) {
		this.groupId = groupId;
		this.groupName = groupName;
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
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Group) && groupId.equals(((Group)o).groupId);
	}
	
	@Override
	public int hashCode() {
		return groupId.hashCode();
	}
	
	@Override
	public String toString() {
		return "Group[groupId=" + groupId + ", groupName=" + groupName + "]";
	}
	
}
