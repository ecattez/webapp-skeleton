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
package fr.ecattez.resource.standard;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Un service qui étend PathAccessor permet d'associer un dossier de destination/récupération à ce service.
 */
public abstract class PathAccessor {
	
	public final static String ROOT_FOLDER = "data";
	public final static String TMP_FOLDER = ROOT_FOLDER + "/tmp";
	
	private final Path ROOT_PATH;
	
	public PathAccessor(final String ROOT_PATH) {
		this.ROOT_PATH = Paths.get(ROOT_FOLDER, ROOT_PATH);
	}
	
	/**
	 * Retourne le chemin racine d'une ressource persistante
	 * 
	 * @return le chemin racine d'une ressource persistante
	 */
	public Path root() {
		return ROOT_PATH;
	}
	
	/**
	 * Retourne le chemin d'accès à une ressource persistante depuis la racine
	 * 
	 * @param	path
	 * 			le chemin d'accès à la ressource sans tenir compte de la racine
	 * 
	 * @return le chemin d'accès d'une ressource persistante depuis la racine
	 */
	public Path fromRoot(Path path) {
		return root().resolve(path);
	}
	
	/**
	 * Retourne le chemin d'accès à une ressource persistante depuis la racine
	 * 
	 * @param	path
	 * 			le chemin d'accès à la ressource sans tenir compte de la racine
	 * 
	 * @return le chemin d'accès d'une ressource persistante depuis la racine
	 */
	public Path fromRoot(String path) {
		return root().resolve(path);
	}

}
