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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.lordrski.dao.impl.PermissionDaoImpl;
import fr.lordrski.exception.PermissionFormatException;

/**
 * Entité représentant une permission d'un groupe à accéder à une certaine ressource d'une certaine manière.
 * Exemple: fictables.get autorise le droit d'accéder à l'uri /fictables en GET
 * Exemple: fictables.* autorise tous les accès à partir de l'uri /fictables
 */
@DatabaseTable(tableName = "permissions", daoClass = PermissionDaoImpl.class)
@Deprecated
public class Permission {
	
	public static final String PERM_REGEX = "^(\\*|\\w+(\\.\\w+)*(\\.\\*)?)$";
	
	@DatabaseField(columnName = "group_id", uniqueCombo = true)
	private String group;
	@DatabaseField(columnName = "permission_id", uniqueCombo = true)
	private String uri;
	
	public Permission() {}
	
	public Permission(String group, String uri) {
		if (!uri.matches(PERM_REGEX)) {
			throw new PermissionFormatException(uri);
		}
		this.group = group;
		this.uri = uri;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * Récupère la permission
	 * 
	 * @return la permission
	 */
	public String getPermission() {
		return uri;
	}

	/**
	 * Saisi la permission
	 * 
	 * @param uri the permission to set
	 */
	public void setPermission(String uri) {
		if (!uri.matches(PERM_REGEX)) {
			throw new PermissionFormatException(uri);
		}
		this.uri = uri;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Permission) && uri.equals(((Permission) o).uri);
	}
	
	@Override
	public int hashCode() {
		return uri == null ? 0 : uri.hashCode();
	}
	
	@Override
	public String toString() {
		return "Permission[uri=" + uri + "]";
	}

}
