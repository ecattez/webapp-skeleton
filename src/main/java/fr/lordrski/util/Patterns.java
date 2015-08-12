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

import java.util.regex.Pattern;

/**
 * Patterns généraux utilisable par toute l'application.
 */
public enum Patterns {
	
	EMAIL("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"),
	PERMISSION("^(\\*|\\w+(\\.\\w+)*(\\.\\*)?)$");
	
	private String regex;
	
	private Patterns(String regex) {
		this.regex = regex;
	}
	
	public String regex() {
		return this.regex;
	}
	
	public Pattern pattern() {
		return Pattern.compile(regex);
	}

}
