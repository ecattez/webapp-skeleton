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

/**
 * StringTool offre quelques fonctionnalités pour manipuler les chaînes de caractères.
 */
public class StringTool {
	
	/**
	 * Empêche l'instanciation de la classe.
	 */
	private StringTool() {}
	
	/**
	 * Test si une chaîne est null
	 * 
	 * @param str la chaîne à tester
	 * @return vrai si la chaîne est null
	 */
	public static boolean isNull(String str) {
		return str == null;
	}
	
	/**
	 * Test si une chaîne est vide
	 * 
	 * @param str la chaîne à tester
	 * @return vrai si la chaîne est vide
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
}
