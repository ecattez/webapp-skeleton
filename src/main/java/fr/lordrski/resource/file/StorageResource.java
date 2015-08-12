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
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import fr.lordrski.mvc.Model;
import fr.lordrski.resource.PathAccessor;
import fr.lordrski.util.AppFiles;
import fr.lordrski.util.Strings;

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
	 * Télécharge un fichier depuis le serveur vers le client
	 * 
	 * @param folder le dossier qui contient le fichier à télécharger
	 * @param filename le nom du fichier à télécharger
	 * @return La réponse avec le fichier dans l'entête ou NOT FOUND
	 */
	@GET
	@Path("download/{folder}/{filename}")
	public Response download(@PathParam("folder") String folder, @PathParam("filename") String filename) {
		java.nio.file.Path filePath = Paths.get(ROOT_PATH, folder, filename);
		if (Files.exists(filePath)) {
			return Response.ok(filePath.toFile())
					.header("Content-Disposition", "attachment; filename=\"" + filePath.getFileName() + "\"").build();			
		}
		throw new NotFoundException();
	}
	
	/**
	 * Télécharge un fichier depuis le serveur vers le client
	 * 
	 * @param filename le nom du fichier à télécharger
	 * @return La réponse avec le fichier dans l'entête ou NOT FOUND
	 */
	@GET
	@Path("download/{filename}")
	public Response download(@PathParam("filename") String filename) {
		return download("", filename);
	}
	
	/**
	 * Télécharge un ou plusieurs fichiers depuis le client vers le serveur
	 * 
	 * @param multiPart L'objet qui contient toutes les informations du formulaire de type multipart
	 * @param folder le dossier de destination
	 * @return La réponse avec la liste des fichiers et leur état succès ou échec de téléchargement
	 */
	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response upload(FormDataMultiPart multiPart, @FormDataParam("folder") @DefaultValue("") String folder) {
		java.nio.file.Path dest = Paths.get(ROOT_PATH, folder);
		Model model = new Model();
		multiPart.getFields().values().forEach(
			fields -> fields.stream().forEach(
				field -> {
					String filename = field.getFormDataContentDisposition().getFileName();
					if (Strings.isNotEmpty(filename)) {
						boolean result = AppFiles.upload(field.getValueAs(File.class).toPath(), dest.resolve(filename));
						model.put(filename, result);
					}
				}
			)
		);
		return Response.ok(model).build();
	}
	
}
