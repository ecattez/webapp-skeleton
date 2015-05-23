package fr.lordrski.resources;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

import fr.lordrski.util.Items;

/**
 * Exemple d'utilisation du {@link Viewable} avec l'objet {@link Items}
 * 
 * @author Edouard CATTEZ (la7production)
 */
@Path("hello")
public class HelloResource {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response render(@Context ServletContext context) {
		Items items = new Items();
		Map<String, String> map = new HashMap<String, String>();
		map.put("john", "doe");
		map.put("barry", "allen");
		items.put("names", map);
		return Response.ok(new Viewable("sample", items)).build();
	}
	
	@GET
	@Path("{user}")
	@Produces(MediaType.TEXT_HTML)
	public Response render(@PathParam("user") String user) {
		return Response.ok(new Viewable("sample", "hello " + user)).build();
	}

}
