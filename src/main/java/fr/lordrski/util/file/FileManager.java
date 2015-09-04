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
package fr.lordrski.util.file;

import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Cette classe offre des fonctionnalités pour manipuler les fichiers principalement via leurs chemins d'accès.
 */
public final class FileManager {
	
	/**
	 * Empêche l'instanciation de la classe.
	 */
	private FileManager() {}
	
	/**
	 * Crée l'ensemble des dossiers formant le chemin d'accès passé en paramètre
	 * 
	 * @param	path
	 * 			les dossiers à créer sous forme d'un chemin d'accès
	 * 
	 * @return le chemin d'accès
	 */
	public static Path mkdirs(Path path) {
		try {
			 Files.createDirectories(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * Archive un fichier ou un dossier
	 * 
	 * @param	src
	 * 			le chemin d'accès du fichier à archiver
	 * @param	dest
	 * 			le chemin d'accès du répertoire temporaire
	 * 
	 * @return le chemin d'accès de l'archive
	 */
	public static Path mkzip(Path src, Path dest) {
		Map<String, Object> env = new HashMap<>();
		env.put("create", "true");
		env.put("useTempFile", true);
		
		Path destParent = dest.getParent();
		if (!Files.exists(destParent)) {
			mkdirs(destParent);
		}
		
		URI uri = URI.create("jar:" + dest.toUri());
		
		try (FileSystem fs = FileSystems.newFileSystem(uri, env)) {
			Iterable<Path> roots = fs.getRootDirectories();
			Path root = roots.iterator().next();
			Files.walkFileTree(src, new CopyFileVisitor(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dest;
	}
	
	/**
	 * Supprime le fichier accessible via le chemin passé en paramètre.
	 * Attention: si le fichier est un dossier, on supprime le dossier récursivement.
	 * 
	 * @param	path
	 * 			le chemin d'accès du fichier à supprimer
	 * 
	 * @return le chemin d'accès
	 */
	public static Path delete(Path path) {
		try {
			Files.walkFileTree(path, new DeleteFileVisitor());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * Copie un fichier à un emplacement sur le disque
	 * 
	 * @param	src
	 * 			le chemin du fichier à copier stocké dans la zone temporaire du disque
	 * @param	dest
	 * 			le chemin du fichier de destination
	 * 
	 * @return vrai si la copie s'est bien déroulée
	 */
	public static boolean copy(Path src, Path dest) {
		Path destParent = dest.getParent();
		if (!Files.exists(destParent)) {
			mkdirs(destParent);
		}
		try (FileChannel fchin = FileChannel.open(src);
				FileChannel fchout = FileChannel.open(dest, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
			return fchin.transferTo(0, fchin.size(), fchout) > 0;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Transforme un fichier JSON sauvegardé sur le disque en objet JAVA
	 * 
	 * @param	jsonPath
	 * 			le chemin d'accès du fichier json à lire
	 * @param	type
	 * 			la classe de l'objet JAVA a retrouver
	 * 
	 * @return l'objet JAVA retrouvé via le fichier json
	 */
	public static <E> E readJSON(Path jsonPath, Class<E> type) {
		ObjectMapper mapper = new ObjectMapper();
		E elt = null;
		if (Files.exists(jsonPath)) {
			try {
				elt = mapper.readValue(jsonPath.toFile(), type);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return elt;
	}
	
	/**
	 * Transforme un objet JAVA en fichier JSON sauvegardé sur le disque
	 * 
	 * @param	jsonPath
	 * 			le chemin d'accès du fichier json à sauvegarder
	 * @param	o
	 * 			l'objet à transformer
	 * 
	 * @return vrai si l'écriture du fichier s'est bien déroulée
	 */
	public static boolean writeJSON(Path jsonPath, Object o) {
		ObjectMapper mapper = new ObjectMapper();
		Path parent = jsonPath.getParent();
		boolean success = false;
		if (!Files.exists(parent)) {
			mkdirs(parent);
		}
		try {
			mapper.writeValue(jsonPath.toFile(), o);
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return success;
	}

}
