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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Folder indique les différents dossiers pouvant être accessible en dehors du contexte.
 */
public enum Folder {
	
	CONFIG("config"), EXCHANGE("exchange");
	
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
	
	/**
	 * Crée le chemin d'accès du dossier s'il n'existe pas
	 * @return le chemin d'accès du dossier
	 */
	public Path mkdirs() {
		try {
			return Files.createDirectories(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * Liste le contenu du dossier
	 * @return la liste des chemins (équivalent aux fichiers) accessibles à partir de ce dossier
	 */
	public List<Path> list() {
		try {
			return Files.list(path).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String toString() {
		return resourcePath;
	}

}
