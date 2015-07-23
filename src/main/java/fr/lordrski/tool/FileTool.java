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
package fr.lordrski.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * FileTool offre quelques fonctionnalités pour manipuler les fichiers
 */
public class FileTool {
	
	/**
	 * Empêche l'instanciation de la classe.
	 */
	private FileTool() {}
	
	/**
	 * Transforme un fichier JSON sauvegardé sur le disque en objet JAVA
	 * @param jsonFile le fichier json à lire
	 * @param type la classe de l'objet JAVA a retrouver
	 * @return l'objet JAVA retrouvé via le fichier json
	 */
	public static <E> E readJSON(File jsonFile, Class<E> type) {
		ObjectMapper mapper = new ObjectMapper();
		E elt = null;
		if (jsonFile.exists()) {
			try {
				elt = mapper.readValue(jsonFile, type);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return elt;
	}
	
	/**
	 * Transforme un objet JAVA en fichier JSON sauvegardé sur le disque
	 * @param jsonFile le fichier json à sauvegarder
	 * @param o l'objet a transformer
	 * @return vrai si l'écriture du fichier s'est bien déroulée
	 */
	public static boolean writeJSON(File jsonFile, Object o) {
		ObjectMapper mapper = new ObjectMapper();
		boolean success = false;
		try {
			jsonFile.getAbsoluteFile().getParentFile().mkdirs();
			mapper.writeValue(jsonFile, o);
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	/**
	 * Copie un fichier à un emplacement sur le disque
	 * 
	 * @param src le fichier à copier stocké dans la zone temporaire du disque
	 * @param dest le chemin de destination
	 * @return vrai si la copie s'est bien déroulée
	 */
	public static boolean upload(File src, Path dest) {
		try (FileInputStream fin = new FileInputStream(src);
				FileOutputStream fout = new FileOutputStream(dest.toFile())) {
			FileChannel fchin = fin.getChannel();
			FileChannel fchout = fout.getChannel();
			fchin.transferTo(0, fchin.size(), fchout);
			return true;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
