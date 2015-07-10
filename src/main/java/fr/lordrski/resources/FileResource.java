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
package fr.lordrski.resources;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.lordrski.util.FileTool;
import fr.lordrski.util.Model;

/**
* Ressource associée aux fichiers
*/
@Singleton
@Path("files")
public class FileResource {
	
	@Context
	private ServletContext context;
	
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
		folder = toRealFolder(folder);
		File file = new File(folder, filename);
		if (file.exists()) {
			return Response.ok(file).header("Content-Disposition", "attachment; filename=\"" + filename + "\"").build();			
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
		return download(null, filename);
	}
	
	/**
	 * Télécharge un ou plusieurs fichiers depuis le client vers le serveur
	 * 
	 * @param multiPart L'objet qui contient toutes les informations du formulaire de type multipart
	 * @param folder le dossier de destination
	 * @return La réponse avec la liste des fichiers et leur état succès ou échec de téléchargement
	 */
	@POST
	@Path("upload/{folder}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response upload(FormDataMultiPart multiPart, @PathParam("folder") String folder) {
		folder = toRealFolder(folder);
		new File(folder).mkdirs();
		String filename;
		Model result = new Model();
		Collection<List<FormDataBodyPart>> allFields = multiPart.getFields().values();
		for (List<FormDataBodyPart> fields : allFields) {
			for (FormDataBodyPart field : fields) {
				filename = field.getFormDataContentDisposition().getFileName();
				if (filename == null || filename.length() == 0)
					continue;
				result.put(filename, FileTool.upload(field.getValueAs(File.class), folder, filename));
			}
		}
		return Response.ok(result).build();
	}
	
	/**
	 * Télécharge un ou plusieurs fichiers depuis le client vers le serveur
	 * 
	 * @param multiPart L'objet qui contient toutes les informations du formulaire de type multipart
	 * @return La réponse avec la liste des fichiers et leur état succès ou échec de téléchargement
	 */
	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response upload(FormDataMultiPart multiPart) {
		return upload(multiPart, null);
	}
	
	/**
	 * Télécharge un document json depusi le client vers le serveur
	 * 
	 * @param folder le dossier de destination
	 * @param filename le nom du fichier de destination
	 * @param json le document json
	 * @return La réponse avec la liste des fichiers et leur état succès ou échec de téléchargement
	 */
	@POST
	@Path("uploadJSON/{folder}/{filename}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response upload(@PathParam("folder") String folder, @PathParam("filename") String filename, String json) {
		folder = toRealFolder(folder);
		new File(folder).mkdirs();
		Model result = new Model();
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode tree = mapper.readTree(json);
			mapper.writeValue(new File(folder, filename), tree);
			result.put(filename, true);
		} catch (IOException e) {
			e.printStackTrace();
			result.put(filename, false);
		}
		return Response.ok(result).build();
	}
	
	/**
	 * Associe le dossier passé en paramètre à son emplacement réel sur le disque
	 * 
	 * @param folder le dossier
	 * @return l'emplacement réel du dossier le disque sous forme de chaîne de caractères
	 */
	private String toRealFolder(String folder) {
		String exchange = (String) context.getAttribute("exchange");
		if (folder == null || folder.length() == 0)
			return exchange;
		return exchange + folder;
	}

}
