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
package fr.lordrski.entity.fictable;

/**
 * FictableInfos représente les informations d'une colonne d'une {@link fr.lordrski.entity.fictable.Fictable}.
 */
public class FictableInfos {
	
	private String name;
	private String type;
	private int length;
	
	public FictableInfos() {}
	
	public FictableInfos(String name, String type, int length) {
		this.name = name;
		this.type = type;
		this.length = length;
	}
	
	/**
	 * Récupère le nom de la colonne
	 * @return le nom de la colonne
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Saisi le nom de la colonne
	 * @param name le nouveau nom de la colonne
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Récupère le type (int, string...) de la colonne
	 * @return le type de la colonne
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Saisi le type (int, string...) de la colonne
	 * @param type le nouveau type de la colonne
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Récupère la taille (nb de caractères) de la colonne
	 * @return la taille de la colonne
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Saisi la taille (nb de caractères) de la colonne
	 * @param length la nouvelle taille de la colonne
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Fictable) && name.equals(((FictableInfos)o).name);
	}
	
	@Override
	public int hashCode() {
		return name == null ? 0 : name.hashCode();
	}
	
	@Override
	public String toString() {
		return "FictableInfos[name=" + name + ", type=" + type + ", length=" + length + "]";
	}

}
