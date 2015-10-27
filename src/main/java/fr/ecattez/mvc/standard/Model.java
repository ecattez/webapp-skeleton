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
package fr.ecattez.mvc.standard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link HashMap} avec des clés de type String et des valeurs Object.
 * Elle est principalement utilisée dans les vues de type {@link org.glassfish.jersey.server.mvc.Viewable}.
 */
public class Model extends HashMap<String, Object> {

	private static final long serialVersionUID = -2809324595272091239L;
	
	public Model() {}
	
	public boolean isNull(String key) {
		return get(key) == null;
	}
	
	public boolean isByte(String key) {
		return get(key) instanceof Byte;
	}
	
	public boolean isShort(String key) {
		return get(key) instanceof Short;
	}
	
	public boolean isInteger(String key) {
		return get(key) instanceof Integer;
	}
	
	public boolean isDouble(String key) {
		return get(key) instanceof Double;
	}
	
	public boolean isFloat(String key) {
		return get(key) instanceof Float;
	}
	
	public boolean isLong(String key) {
		return get(key) instanceof Long;
	}
	
	public boolean isString(String key) {
		return get(key) instanceof String;
	}
	
	public boolean isArray(String key) {
		return get(key) instanceof Object[];
	}
	
	public boolean isList(String key) {
		return get(key) instanceof List;
	}
	
	public boolean isMap(String key) {
		return get(key) instanceof Map;
	}

}
