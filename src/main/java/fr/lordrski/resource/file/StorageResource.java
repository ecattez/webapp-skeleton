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
package fr.lordrski.resource.file;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import fr.lordrski.mvc.Model;
import fr.lordrski.resource.PathAccessor;
import fr.lordrski.util.Patterns;
import fr.lordrski.util.Strings;
import fr.lordrski.util.file.FileManager;

/**
* Service associé à la persistence des données sous forme de fichiers.
* Ce service utilise comme dossier d'origine le dossier {@link PathAccessor.ROOT_FOLDER}/storage.
*/
@Singleton
@Path("storage")
public class StorageResource extends PathAccessor {
	
	public StorageResource() {
		super("storage");
	}
	
	/**
	 * Télécharge un ou plusieurs fichiers depuis le client vers le serveur
	 * 
	 * @param	multiPart
	 * 			l'objet qui contient toutes les informations du formulaire de type multipart
	 * @param	path
	 * 			le dossier de destination
	 * 
	 * @return la réponse avec la liste des fichiers et leur état succès ou échec de téléchargement
	 */
	@POST
	@Path("{path:" + Patterns.Constants.URI + "}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createFile(@PathParam("path") String path, FormDataMultiPart multiPart) {
		java.nio.file.Path dest = Paths.get(ROOT_PATH, path);
		Model model = new Model();
		multiPart.getFields().values().forEach(
			fields -> fields.stream().forEach(
				field -> {
					String filename = field.getFormDataContentDisposition().getFileName();
					if (!Strings.isEmpty(filename)) {
						boolean result = FileManager.copy(field.getValueAs(File.class).toPath(), dest.resolve(filename));
						model.put(filename, result);
					}
				}
			)
		);
		return Response.ok(model).build();
	}
	
	/**
	 * Télécharge un fichier depuis le serveur vers le client.
	 * Dans le cas d'un dossier, il est envoyé sous forme d'une archive ZIP.
	 * 
	 * @param	path
	 * 			le chemin du fichier à télécharger
	 * 
	 * @return la réponse avec le fichier dans l'entête ou NOT FOUND
	 */
	@GET
	@Path("{path:" + Patterns.Constants.URI + "}")
	public Response getFile(@PathParam("path") String path) {
		java.nio.file.Path src = Paths.get(ROOT_PATH, path);
		if (Files.exists(src)) {
			if (Files.isDirectory(src)) {
				src = FileManager.mkzip(src, Paths.get(TMP_FOLDER).resolve(src.getFileName() + ".zip"));
			}
			return Response.ok(src.toFile()).header("Content-Disposition", "attachment; filename=\"" + src.getFileName() + "\"").build();
		}
		throw new NotFoundException();
	}
	
	/**
	 * Supprime un fichier du serveur
	 * 
	 * @param	path
	 * 			le chemin du fichier à supprimer
	 * 
	 * @return la réponse de suppression du fichier
	 */
	@DELETE
	@Path("{path:" + Patterns.Constants.URI + "}")
	public Response deleteFile(@PathParam("path") String path) {
		java.nio.file.Path src = Paths.get(ROOT_PATH, path);
		if (Files.exists(src)) {
			return Response.ok(FileManager.delete(src).toString()).build();
		}
		throw new NotFoundException();
	}
	
}
