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

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Viewable;

/**
 * Service associé à divers objets
 */
@Singleton
@Path("/")
public class MiscResource {
	
	/**
	 * Accède à la page d'index de l'application
	 * 
	 * @return La vue de la page d'index de l'application
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable index() {
		return new Viewable("index", "Hi dude!");
	}
	
	/**
	 * Accède à l'index de la ressource UserResource
	 * 
	 * @return La vue de la page d'index de UserResource
	 */
	@GET
	@Path("index_users")
	public Viewable index_users() {
		return new Viewable("users");
	}
	
	/**
	 * Accède à l'index de la ressource FileResource
	 * 
	 * @return La vue de la page d'index de FileResource
	 */
	@GET
	@Path("index_files")
	@Produces(MediaType.TEXT_HTML)
	public Viewable index_files() {
		return new Viewable("files");
	}

}
