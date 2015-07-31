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
package fr.lordrski.entity;

import java.util.Set;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.lordrski.dao.impl.GroupDaoImpl;

/**
 * Entité représentant un groupe d'utilisateurs dans une compagnie.
 */
@DatabaseTable(tableName = "groups", daoClass = GroupDaoImpl.class)
public class Group {
	
	@DatabaseField(columnName = "company_id", uniqueCombo = true)
	private String company;
	@DatabaseField(columnName = "group_id", id = true, uniqueCombo = true)
	private String groupId;
	@DatabaseField(columnName = "group_label")
	private String groupLabel;
	
	private Set<Permission> permissions;
	
	private Set<User> users;
	
	public Group() {}
	
	public Group(String company, String groupId, String groupLabel) {
		this.company = company;
		this.groupId = groupId;
		this.groupLabel = groupLabel;
	}
	
	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupLabel
	 */
	public String getGroupLabel() {
		return groupLabel;
	}

	/**
	 * @param groupLabel the groupLabel to set
	 */
	public void setGroupLabel(String groupLabel) {
		this.groupLabel = groupLabel;
	}

	/**
	 * @return the permissions
	 */
	public Set<Permission> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	/**
	 * 
	 * @param permission
	 * @return
	 */
	public boolean add(Permission permission) {
		return permissions.add(permission);
	}
	
	/**
	 * 
	 * @param permission
	 * @return
	 */
	public boolean remove(Permission permission) {
		return permissions.remove(permission);
	}

	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public boolean add(User user) {
		return users.add(user);
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public boolean remove(User user) {
		return users.remove(user);
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Group) && groupId.equals(((Group) o).groupId);
	}
	
	@Override
	public int hashCode() {
		return groupId == null ? 0 : groupId.hashCode();
	}
	
	@Override
	public String toString() {
		return "Group[company=" + company + ", groupId=" + groupId
					+ ", groupLabel=" + groupLabel + ", permissions=" + permissions + ", users=" + users + "]";
	}

}
