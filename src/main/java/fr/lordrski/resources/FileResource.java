package fr.lordrski.resources;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.server.mvc.Viewable;

@Singleton
@Path("file")
public class FileResource {
	
	@GET
	@Produces("text/html")
	public Response index() {
		return Response.ok(new Viewable("file")).build();
	}
	
	@GET
	@Path("download")
	public Response download(@QueryParam("filename") String filename) {
		File file = new File(System.getProperty("user.dir") + File.separator + filename);
		if (file.exists()) {
			return Response.ok(file).header("Content-Disposition", "attachment; filename=\"" + filename + "\"").build();			
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@POST
	@Path("upload")
	public Response upload(@FormDataParam("file") InputStream in, @FormDataParam("file") FormDataContentDisposition content) {
		try {
			Files.copy(in, new File(System.getProperty("user.dir") + File.separator + content.getFileName()).toPath(), REPLACE_EXISTING);
		} catch (IOException e1) {
			return Response.serverError().build();
		}
		return Response.ok().entity("File saved successfuly").build();
	}

}
