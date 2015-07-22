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

import javax.servlet.ServletContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.lordrski.util.Folder;

/**
 * FileTool offre quelques fonctionnalités pour manipuler les fichiers
 */
public class FileTool {
	
	/**
	 * Empêche l'instanciation de la classe.
	 */
	private FileTool() {}
	
	/**
	 * Associe le dossier passé en paramètre à son emplacement réel sur le disque
	 * 
	 * @param context le contexte de l'application web
	 * @param folder le dossier sur le disque
	 * @param dir le dossier
	 * @return l'emplacement réel du dossier le disque sous forme de chaîne de caractères
	 */
	public static String toRealFolder(ServletContext context, Folder folder, String dir) {
		String path = (String) context.getAttribute(folder.toString());
		if (dir == null || dir.length() == 0)
			return path;
		return path + folder;
	}
	
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
	 * Copie un fichier dans un emplacement du disque
	 * 
	 * @param tmp le fichier à copier stocké dans la zone temporaire du disque
	 * @param folder le dossier
	 * @param filename le nom du fichier
	 * @return vrai si la copie s'est bien déroulée
	 */
	public static boolean upload(File tmp, String folder, String filename) {
		try (FileInputStream fin = new FileInputStream(tmp);
				FileOutputStream fout = new FileOutputStream(folder + File.separator + filename)) {
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
