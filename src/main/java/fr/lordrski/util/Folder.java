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

import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Folder indique les différents dossiers pouvant être accessible au travers du contexte.
 */
public enum Folder {
	
	EXCHANGE("exchange");
	
	private Path path;
	private String resourcePath;
	
	private Folder(String resourcePath) {
		this.resourcePath = resourcePath;
		this.path = Paths.get(resourcePath);
	}
	
	/**
	 * Retourne le chemin d'accès du dossier sur le disque
	 * @return le chemin d'accès du dossier présent sur le disque
	 */
	public Path toPath() {
		return path;
	}
	
	/**
	 * Concatène le chemin d'accès du dossier avec un autre chemin
	 * @param other le chemin à ajouter au chemin d'accès du dossier présent sur le disque
	 * @return le chemin d'accès du fichier dans le dossier sous forme d'une chaîne de caractères
	 */
	public Path resolve(String other) {
		if (other == null)
			return path;
		return path.resolve(other);
	}
	
	@Override
	public String toString() {
		return resourcePath;
	}

}
