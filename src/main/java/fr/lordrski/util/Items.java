package fr.lordrski.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Items extends HashMap<String, Object> {

	private static final long serialVersionUID = -2809324595272091239L;
	
	public Items() {}
	
	public Items(Map<String, Object> items) {
		this.putAll(items);
	}
	
	public boolean isByte(String key) {
		return containsKey(key) && get(key) instanceof Byte;
	}
	
	public boolean isShort(String key) {
		return containsKey(key) && get(key) instanceof Short;
	}
	
	public boolean isInteger(String key) {
		return containsKey(key) && get(key) instanceof Integer;
	}
	
	public boolean isDouble(String key) {
		return containsKey(key) && get(key) instanceof Double;
	}
	
	public boolean isFloat(String key) {
		return containsKey(key) && get(key) instanceof Float;
	}
	
	public boolean isString(String key) {
		return containsKey(key) && get(key) instanceof String;
	}
	
	public boolean isArray(String key) {
		return containsKey(key) && get(key) instanceof Object[];
	}
	
	public boolean isList(String key) {
		return containsKey(key) && get(key) instanceof List;
	}
	
	public boolean isMap(String key) {
		return containsKey(key) && get(key) instanceof Map;
	}

}
