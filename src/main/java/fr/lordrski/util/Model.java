package fr.lordrski.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link HashMap} with string keys and object values used to send data throw a {@link org.glassfish.jersey.server.mvc.Viewable}
 * 
 * @author Edouard CATTEZ (la7production)
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
