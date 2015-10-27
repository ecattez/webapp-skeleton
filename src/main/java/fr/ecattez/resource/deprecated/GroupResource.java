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
package fr.ecattez.resource.deprecated;

import java.net.URI;
import java.sql.SQLException;
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

import fr.ecattez.dao.deprecated.GroupDao;
import fr.ecattez.dao.standard.DaoProvider;
import fr.ecattez.entity.deprecated.Group;

/**
 * Service associé à l'entité {@link fr.ecattez.entity.deprecated.Group}
 */
@Path("groups")
public class GroupResource {
	
	@Context
	private UriInfo uriInfo;
	
	private GroupDao groupDao;

	public GroupResource() {
		this.groupDao = DaoProvider.getDao(Group.class);
	}

	@POST
	public Response createGroup(Group group) throws SQLException {
		if (groupDao.find(group.getGroupId()) != null) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		else {
			groupDao.insert(group);
			URI instanceURI = uriInfo.getAbsolutePathBuilder().path(group.getGroupId()).build();
			return Response.created(instanceURI).build();
		}
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Group> getGroups() throws SQLException {
		return groupDao.findAll();
	}

	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Group getGroup(@PathParam("id") String id) throws SQLException {
		Group group = groupDao.find(id);
		if (group == null) {
			throw new NotFoundException();
		}
		return group;
	}

	@DELETE
	@Path("{id}")
	public Response deleteGroup(@PathParam("id") String id) throws SQLException {
		Group group = groupDao.find(id);
		if (group == null) {
			throw new NotFoundException();
		}
		else {
			groupDao.deleteById(id);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}

	@PUT
	@Path("{id}")
	public Response updateGroup(@PathParam("id") String id, Group group) throws SQLException {
		if (groupDao.find(id) == null) {
			throw new NotFoundException();
		}
		else {
			groupDao.update(group);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}

}
