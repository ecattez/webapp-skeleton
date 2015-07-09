package fr.lordrski;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import fr.lordrski.util.Model;

public class ModelTest {
	
	private static Model model = new Model();
	
	@Test
	public void test_isNull() {
		model.clear();
		model.put("key1", null);
		assertTrue(model.isNull("key1"));
	}
	
	@Test
	public void test_isNothing() {
		model.clear();
		model.put("key1", null);
		assertTrue(!model.isByte("key1"));
		assertTrue(!model.isShort("key1"));
		assertTrue(!model.isInteger("key1"));
		assertTrue(!model.isDouble("key1"));
		assertTrue(!model.isFloat("key1"));
		assertTrue(!model.isLong("key1"));
		assertTrue(!model.isString("key1"));
		assertTrue(!model.isArray("key1"));
		assertTrue(!model.isList("key1"));
		assertTrue(!model.isMap("key1"));
	}

	@Test
	public void test_isByte() {
		model.clear();
		model.put("key1", new Byte((byte)100));
		model.put("key2", (byte)100);
		assertTrue(model.isByte("key1"));
		assertTrue(model.isByte("key2"));
	}
	
	@Test
	public void test_isShort() {
		model.clear();
		model.put("key1", new Short((short)12));
		model.put("key2", (short)42);
		assertTrue(model.isShort("key1"));
		assertTrue(model.isShort("key2"));
	}

	@Test
	public void test_isInteger() {
		model.clear();
		model.put("key1", new Integer(12));
		model.put("key2", 42);
		assertTrue(model.isInteger("key1"));
		assertTrue(model.isInteger("key2"));
	}

	@Test
	public void test_isDouble() {
		model.clear();
		model.put("key1", new Double(1.234567890d));
		model.put("key2", 4.234567890d);
		assertTrue(model.isDouble("key1"));
		assertTrue(model.isDouble("key2"));
	}

	@Test
	public void test_isFloat() {
		model.clear();
		model.put("key1", new Float(1.2f));
		model.put("key2", 4.2f);
		assertTrue(model.isFloat("key1"));
		assertTrue(model.isFloat("key2"));
	}
	
	@Test
	public void test_isLong() {
		model.clear();
		model.put("key1", new Long(2809324595272091239L));
		model.put("key2", 2809324595272091239L);
		assertTrue(model.isLong("key1"));
		assertTrue(model.isLong("key2"));
	}

	@Test
	public void test_isString() {
		model.clear();
		model.put("key1", new String("value1"));
		model.put("key2", "value2");
		assertTrue(model.isString("key1"));
		assertTrue(model.isString("key2"));
	}
	
	@Test
	public void test_isArray() {
		model.clear();
		model.put("key1", new Object[] { "value1", 12, 4.2f });
		model.put("key2", new String[] { "value1", "value2", "value3" });
		assertTrue(model.isArray("key1"));
		assertTrue(model.isArray("key2"));
	}
	
	@Test
	public void test_isList() {
		model.clear();
		List<Object> listO = new ArrayList<Object>();
		listO.add("value1");
		listO.add(12);
		listO.add(4.2f);
		List<String> listS = new ArrayList<String>();
		listS.add("value1");
		listS.add("value2");
		model.put("key1", listO);
		model.put("key2", listS);
		assertTrue(model.isList("key1"));
		assertTrue(model.isList("key2"));
	}
	
	@Test
	public void test_isMap() {
		model.clear();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keymap1", 12);
		map.put("keymap2", 4.2f);
		map.put("keymap3", "value3");
		model.put("key", map);
		assertTrue(model.isMap("key"));
	}

}
