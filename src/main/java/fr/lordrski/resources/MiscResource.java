package fr.lordrski.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Viewable;

/**
 * Misc resources that we can have in a web application
 * 
 * @author Edouard CATTEZ (la7production)
 */
@Path("/")
public class MiscResource {
	
	/**
	 * Accède à la page d'index de l'application
	 * @return La vue de la page d'index
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable index() {
		return new Viewable("index", "Hi dude!");
	}

}
