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
package fr.lordrski.util;

import junit.framework.TestCase;

import org.junit.Test;

public class NumberTestCase extends TestCase {
	
	@Test
	public void test_alphabetic() {
		assertTrue(!Numbers.isNumber("12a"));
		assertTrue(!Numbers.isNumber("12.a"));
		assertTrue(!Numbers.isNumber("a12"));
		assertTrue(!Numbers.isNumber("a.12"));
	}
	
	@Test
	public void test_zero() {
		assertTrue(Numbers.isNumber("00"));
	}
	
	@Test
	public void test_sign() {
		assertTrue(Numbers.isNumber("+12"));
		assertTrue(Numbers.isNumber("-42"));
		assertTrue(!Numbers.isNumber("+-42"));
	}
	
	@Test
	public void test_int() {
		assertTrue(Numbers.isNumber("12"));
	}
	
	@Test
	public void test_long() {
		assertTrue(Numbers.isNumber("9876543210"));
	}
	
	@Test
	public void test_dot() {
		assertTrue(!Numbers.isNumber("00."));
	}
	
	@Test
	public void test_float() {
		assertTrue(Numbers.isNumber("845.32"));
	}
	
	@Test
	public void test_double() {
		assertTrue(Numbers.isNumber("8.32445645645464546454645454454545"));
	}
	
}
