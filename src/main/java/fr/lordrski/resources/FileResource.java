package fr.lordrski.resources;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;

import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.server.mvc.Viewable;

import fr.lordrski.util.Model;

/**
* Ressource File
* 
* @author Edouard CATTEZ (la7production)
*/
@Singleton
@Path("files")
public class FileResource {
	
	@Context
	private ServletContext context;
	
	/**
	 * Accède à l'index de la ressource
	 * @return La vue de la page d'index de la ressource
	 */
	@GET
	@Produces("text/html")
	public Viewable index() {
		return new Viewable("files");
	}
	
	/**
	 * Télécharge un fichier depuis le serveur vers le client
	 * @param folder le dossier qui contient le fichier
	 * @param filename le nom du fichier
	 * @return La réponse avec le fichier dans l'entête ou l'erreur NOT FOUND
	 */
	@GET
	@Path("download")
	public Response download(@QueryParam("folder") String folder, @QueryParam("filename") String filename) {
		folder = toRealFolder(folder);
		File file = new File(folder + File.separator + filename);
		if (file.exists()) {
			return Response.ok(file).header("Content-Disposition", "attachment; filename=\"" + filename + "\"").build();			
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	/**
	 * Télécharge un ou plusieurs fichiers depuis le client vers le serveur
	 * @param multiPart L'objet qui contient toutes les informations du formulaire de type multipart
	 * @param folder le dossier de destination
	 * @return La réponse avec la liste des fichiers et leur état succès ou échec de téléchargement
	 */
	@POST
	@Path("upload")
	@Produces(MediaType.APPLICATION_JSON)
	public Response upload(FormDataMultiPart multiPart, @QueryParam("folder") String folder) {
		folder = toRealFolder(folder);
		new File(folder).mkdirs();
		String filename;
		Model successAndFails = new Model();
		Collection<List<FormDataBodyPart>> allFields = multiPart.getFields().values();
		for (List<FormDataBodyPart> fields : allFields) {
			for (FormDataBodyPart field : fields) {		
				filename = field.getFormDataContentDisposition().getFileName();
				if (filename == null || filename.length() == 0)
					continue;
				successAndFails.put(filename, upload(field.getValueAs(InputStream.class), folder, filename));
			}
		}
		return Response.ok(successAndFails).build();
	}
	
	/**
	 * Associe le dossier passé en paramètre à son emplacement réel sur le disque
	 * @param folder le dossier
	 * @return l'emplacement réel du dossier le disque sous forme de chaîne de caractères
	 */
	private String toRealFolder(String folder) {
		String exchange = (String) context.getAttribute("exchange");
		if (folder == null || folder.length() == 0)
			return exchange;
		return exchange + folder;
	}
	
	/**
	 * Copie un fichier dans un emplacement du disque
	 * @param in le flux de données
	 * @param folder le dossier
	 * @param filename le nom du fichier
	 * @return vrai si la copie s'est bien déroulée
	 */
	private boolean upload(InputStream in, String folder, String filename) {
		try {
			Files.copy(in, new File(folder + File.separator + filename).toPath(), REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
			in.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}
