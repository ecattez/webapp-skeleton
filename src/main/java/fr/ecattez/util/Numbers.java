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
package fr.ecattez.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Numbers offre des fonctionnalités pour manipuler les nombres
 */
public final class Numbers {
	
	/**
	 * Empêche l'instanciation de la classe.
	 */
	private Numbers() {}
	
	/**
	 * Test si une chaîne est un nombre
	 * 
	 * @param	str
	 * 			la chaîne à tester
	 * 
	 * @return vrai si la chaîne est un nombre
	 */
	public static boolean isNumber(String str) {
		if (Strings.isEmpty(str)) {
			return false;
		}
		return str.matches("[+-]?\\d+(\\.\\d+)?");
	}
	
	/**
	 * Transforme une chaîne en byte
	 * 
	 * @param	str
	 * 			la chaîne à transformer
	 * 
	 * @return le byte réprésenté par la chaîne ou 0 en cas d'erreur
	 */
	public static byte parseByte(String str) {
		try {
			return Byte.parseByte(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	/**
	 * Transforme une chaîne en short
	 * 
	 * @param	str
	 * 			la chaîne à transformer
	 * 
	 * @return le short réprésenté par la chaîne ou 0 en cas d'erreur
	 */
	public static short parseShort(String str) {
		try {
			return Short.parseShort(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	/**
	 * Transforme une chaîne en int
	 * 
	 * @param	str
	 * 			la chaîne à transformer
	 * 
	 * @return l'int réprésenté par la chaîne ou 0 en cas d'erreur
	 */
	public static int parseInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	/**
	 * Transforme une chaîne en long
	 * 
	 * @param	str
	 * 			la chaîne à transformer
	 * 
	 * @return le long réprésenté par la chaîne ou 0 en cas d'erreur
	 */
	public static long parseLong(String str) {
		try {
			return Long.parseLong(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	/**
	 * Transforme une chaîne en float
	 * 
	 * @param	str
	 * 			la chaîne à transformer
	 * 
	 * @return le float réprésenté par la chaîne ou 0 en cas d'erreur
	 */
	public static float parseFloat(String str) {
		try {
			return Float.parseFloat(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	/**
	 * Transforme une chaîne en double
	 * 
	 * @param	str
	 * 			la chaîne à transformer
	 * 
	 * @return le double réprésenté par la chaîne ou 0 en cas d'erreur
	 */
	public static double parseDouble(String str) {
		try {
			return Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	/**
	 * Transforme une chaîne en BigDecimal
	 * 
	 * @param	str
	 * 			la chaîne à transformer
	 * 
	 * @return le BigDecimal réprésenté par la chaîne ou 0 en cas d'erreur
	 */
	public static BigDecimal parseBigDecimal(String str) {
		if (isNumber(str)) {
			return new BigDecimal(str);
		}
		return null;
	}
	
	/**
	 * Transforme une chaîne en BigInteger
	 * 
	 * @param	str
	 * 			la chaîne à transformer
	 * 
	 * @return le BigInteger réprésenté par la chaîne ou 0 en cas d'erreur
	 */
	public static BigInteger parseBigInteger(String str) {
		if (isNumber(str)) {
			return new BigInteger(str);
		}
		return null;
	}
	
	/**
	 * Transforme une chaîne en Number
	 * 
	 * @param	str
	 * 			la chaîne à transformer
	 * 
	 * @return le Number réprésenté par la chaîne ou 0 en cas d'erreur
	 */
	public static Number parseNumber(String str) {
		try {
			return NumberFormat.getInstance().parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

}
