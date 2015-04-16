package fr.lordrski.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

@Path("/")
public class MainResource {
	
	@GET
	@Produces("text/html")
	public Response index() {
		return Response.ok(new Viewable("/index")).build();
	}
	
	@GET
	@Path("/mysession")
	public String servlet(@Context HttpServletRequest context) {
		return context.getSession().getId();
	}

}
