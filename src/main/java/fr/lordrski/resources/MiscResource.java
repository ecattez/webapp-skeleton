package fr.lordrski.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

/**
 * Misc resources that we can have in a web application
 * 
 * @author Edouard CATTEZ (la7production)
 */
@Path("/")
public class MiscResource {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response index() {
		return Response.ok(new Viewable("index", "Hi dude!")).build();
	}

}
