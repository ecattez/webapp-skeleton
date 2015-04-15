package fr.lordrski;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import fr.lordrski.util.Items;

public class ItemsTest {
	
	private static Items items = new Items();
	
	@Test
	public void test_isNull() {
		items.clear();
		items.put("key1", null);
		assertTrue(items.isNull("key1"));
	}
	
	@Test
	public void test_isNothing() {
		items.clear();
		items.put("key1", null);
		assertTrue(!items.isByte("key1"));
		assertTrue(!items.isShort("key1"));
		assertTrue(!items.isInteger("key1"));
		assertTrue(!items.isDouble("key1"));
		assertTrue(!items.isFloat("key1"));
		assertTrue(!items.isLong("key1"));
		assertTrue(!items.isString("key1"));
		assertTrue(!items.isArray("key1"));
		assertTrue(!items.isList("key1"));
		assertTrue(!items.isMap("key1"));
	}

	@Test
	public void test_isByte() {
		items.clear();
		items.put("key1", new Byte((byte)100));
		items.put("key2", (byte)100);
		assertTrue(items.isByte("key1"));
		assertTrue(items.isByte("key2"));
	}
	
	@Test
	public void test_isShort() {
		items.clear();
		items.put("key1", new Short((short)12));
		items.put("key2", (short)42);
		assertTrue(items.isShort("key1"));
		assertTrue(items.isShort("key2"));
	}

	@Test
	public void test_isInteger() {
		items.clear();
		items.put("key1", new Integer(12));
		items.put("key2", 42);
		assertTrue(items.isInteger("key1"));
		assertTrue(items.isInteger("key2"));
	}

	@Test
	public void test_isDouble() {
		items.clear();
		items.put("key1", new Double(1.234567890d));
		items.put("key2", 4.234567890d);
		assertTrue(items.isDouble("key1"));
		assertTrue(items.isDouble("key2"));
	}

	@Test
	public void test_isFloat() {
		items.clear();
		items.put("key1", new Float(1.2f));
		items.put("key2", 4.2f);
		assertTrue(items.isFloat("key1"));
		assertTrue(items.isFloat("key2"));
	}
	
	@Test
	public void test_isLong() {
		items.clear();
		items.put("key1", new Long(2809324595272091239L));
		items.put("key2", 2809324595272091239L);
		assertTrue(items.isLong("key1"));
		assertTrue(items.isLong("key2"));
	}

	@Test
	public void test_isString() {
		items.clear();
		items.put("key1", new String("value1"));
		items.put("key2", "value2");
		assertTrue(items.isString("key1"));
		assertTrue(items.isString("key2"));
	}
	
	@Test
	public void test_isArray() {
		items.clear();
		items.put("key1", new Object[] { "value1", 12, 4.2f });
		items.put("key2", new String[] { "value1", "value2", "value3" });
		assertTrue(items.isArray("key1"));
		assertTrue(items.isArray("key2"));
	}
	
	@Test
	public void test_isList() {
		items.clear();
		List<Object> listO = new ArrayList<Object>();
		listO.add("value1");
		listO.add(12);
		listO.add(4.2f);
		List<String> listS = new ArrayList<String>();
		listS.add("value1");
		listS.add("value2");
		items.put("key1", listO);
		items.put("key2", listS);
		assertTrue(items.isList("key1"));
		assertTrue(items.isList("key2"));
	}
	
	@Test
	public void test_isMap() {
		items.clear();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keymap1", 12);
		map.put("keymap2", 4.2f);
		map.put("keymap3", "value3");
		items.put("key", map);
		assertTrue(items.isMap("key"));
	}

}
