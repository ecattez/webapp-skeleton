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

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import fr.lordrski.entity.fictables.Fictable;
import fr.lordrski.util.AppFiles;

/**
 * Service associé aux fictables
 */
@Path("fictables")
public class FictableResource extends PathAccessor {
	
	@Context
	private UriInfo uriInfo;
	
	public FictableResource() {
		super("fictables");
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fictable> getFictables() throws IOException {
		java.nio.file.Path parent = Paths.get(ROOT_PATH);
		List<Fictable> fictables = new ArrayList<Fictable>();
		if (Files.isDirectory(parent)) {
			Files.list(parent).forEach(
				jsonPath -> {
					Fictable tmp = AppFiles.readJSON(jsonPath, Fictable.class);
					if (tmp != null) {
						fictables.add(tmp);
					}
				}
			);
		}
		return fictables;
	}
	
	@GET
	@Path("{fictable}")
	@Produces(MediaType.APPLICATION_JSON)
	public Fictable getFictable(@PathParam("fictable") String jsonName) {
		java.nio.file.Path jsonPath = Paths.get(ROOT_PATH, Fictable.normalize(jsonName));
		Fictable fictable = AppFiles.readJSON(jsonPath, Fictable.class);
		if (fictable == null) {
			throw new NotFoundException();
		}
		return fictable;
	}
	
	@POST
	public Response createFictable(Fictable fictable) {
		java.nio.file.Path jsonPath = Paths.get(ROOT_PATH, fictable.getFileName());
		if (Files.exists(jsonPath)) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		else {
			AppFiles.writeJSON(jsonPath, fictable);
			URI instanceURI = uriInfo.getAbsolutePathBuilder().path(fictable.getFileName()).build();
			return Response.created(instanceURI).build();
		}
	}
	
	@DELETE
	@Path("{fictable}")
	public Response deleteFictable(@PathParam("fictable") String jsonName) throws IOException {
		java.nio.file.Path jsonPath = Paths.get(ROOT_PATH, Fictable.normalize(jsonName));
		if (Files.deleteIfExists(jsonPath)) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		throw new NotFoundException();
	}
	
	@PUT
	@Path("{fictable}")
	public Response updateFictable(@PathParam("fictable") String jsonName, Fictable fictable) {
		java.nio.file.Path jsonPath = Paths.get(ROOT_PATH, Fictable.normalize(jsonName));
		if (Files.exists(jsonPath)) {
			AppFiles.writeJSON(jsonPath, fictable);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		throw new NotFoundException();
	}

}
