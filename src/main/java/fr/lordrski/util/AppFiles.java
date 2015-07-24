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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.lordrski.mvc.Model;

/**
 * AppFiles offre des fonctionnalités pour manipuler les fichiers
 */
public class AppFiles {
	
	/**
	 * Empêche l'instanciation de la classe.
	 */
	private AppFiles() {}
	
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
			jsonFile.getParentFile().mkdirs();
			mapper.writeValue(jsonFile, o);
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	/**
	 * Télécharge un fichier depuis le serveur vers le client
	 * 
	 * @param Path path le chemin d'accès du fichier à télécharger
	 * @return La réponse avec le fichier dans l'entête ou NOT FOUND
	 */
	public static Response download(Path filePath) {
		if (Files.exists(filePath)) {
			return Response.ok(filePath.toFile())
					.header("Content-Disposition", "attachment; filename=\"" + filePath.getFileName() + "\"").build();			
		}
		throw new NotFoundException();
	}
	
	/**
	 * Télécharge un ou plusieurs fichiers depuis le client vers le serveur
	 * 
	 * @param multiPart L'objet qui contient toutes les informations du formulaire de type multipart
	 * @param dest le chemin du dossier de destination
	 * @return La réponse avec la liste des fichiers et leur état succès ou échec de téléchargement
	 */
	public static Response upload(FormDataMultiPart multiPart, Path dest) {
		Model model = new Model();
		multiPart.getFields().values().stream().forEach(
			fields -> fields.stream().filter(field -> !Strings.isEmpty(field.getFormDataContentDisposition().getFileName())).forEach(
				field -> {
					String filename = field.getFormDataContentDisposition().getFileName();
					boolean result = upload(field.getValueAs(File.class), dest.resolve(filename));
					model.put(filename, result);
				}
			)
		);
		return Response.ok(model).build();
	}
	
	/**
	 * Copie un fichier à un emplacement sur le disque
	 * 
	 * @param src le fichier à copier stocké dans la zone temporaire du disque
	 * @param dest le chemin du fichier de destination
	 * @return vrai si la copie s'est bien déroulée
	 */
	public static boolean upload(File src, Path dest) {
		dest.getParent().toFile().mkdirs();
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
