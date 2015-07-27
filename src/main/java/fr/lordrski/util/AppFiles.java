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
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

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
	 * Crée l'ensemble des dossiers formant le chemin d'accès passé en paramètre
	 * @param path les dossiers à créer sous forme d'un chemin d'accès
	 * @return le chemin d'accès créé et valide, null sinon
	 */
	public static Path mkdirs(Path path) {
		Path out = null;
		try {
			out = Files.createDirectories(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	
	/**
	 * Transforme un fichier JSON sauvegardé sur le disque en objet JAVA
	 * @param jsonPath le chemin d'accès du fichier json à lire
	 * @param type la classe de l'objet JAVA a retrouver
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
	 * @param jsonPath le chemin d'accès du fichier json à sauvegarder
	 * @param o l'objet à transformer
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
					boolean result = upload(field.getValueAs(File.class).toPath(), dest.resolve(filename));
					model.put(filename, result);
				}
			)
		);
		return Response.ok(model).build();
	}
	
	/**
	 * Copie un fichier à un emplacement sur le disque
	 * 
	 * @param src le chemin du fichier à copier stocké dans la zone temporaire du disque
	 * @param dest le chemin du fichier de destination
	 * @return vrai si la copie s'est bien déroulée
	 */
	public static boolean upload(Path src, Path dest) {
		Path parent = dest.getParent();
		if (!Files.exists(parent)) {
			mkdirs(parent);
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

}
