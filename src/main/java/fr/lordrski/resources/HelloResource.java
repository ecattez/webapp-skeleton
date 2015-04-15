package fr.lordrski.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

import fr.lordrski.util.Items;

@Path("hello")
public class HelloResource {
	
	@GET
	@Produces("text/html")
	public Response index() {
		Items items = new Items();
		items.put("names", new String[] { "LordRski", "SeajaK", "Piu" });
		return Response.ok(new Viewable("/hello", items)).build();
	}

}
