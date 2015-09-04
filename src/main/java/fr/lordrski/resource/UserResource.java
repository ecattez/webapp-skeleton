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
package fr.lordrski.resource;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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

import fr.lordrski.dao.UserDao;
import fr.lordrski.entity.deprecated.User;
import fr.lordrski.util.sql.DaoProvider;

/**
 * Service associé à l'entité {@link fr.lordrski.entity.deprecated.User}
 */
@Path("users")
public class UserResource {
	
	@Context
	private UriInfo uriInfo;
	
	private UserDao userDao;

	/**
	 * Une ressource doit avoir un contructeur (éventuellement sans arguments)
	 */
	public UserResource() {
		this.userDao = DaoProvider.getDao(User.class);
	}

	/**
	 * Méthode de création d'un utilisateur qui prend en charge les requêtes HTTP POST
	 * La méthode renvoie l'URI de la nouvelle instance en cas de succès
	 *
	 * @param  user Instance d'utilisateur à créer
	 * @return Response le corps de la réponse est vide, le code de retour HTTP est fixé à 201 si la création est faite
	 *         L'en-tête contient un champs Location avec l'URI de la nouvelle ressource
	 * @throws SQLException 
	 */
	@POST
	public Response createUser(User user) throws SQLException {
		// Si l'utilisateur existe déjà, renvoyer 409
		if (userDao.find(user.getLogin()) != null) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		else {
			userDao.insert(user);
			// On renvoie 201 et l'instance de la ressource dans le Header HTTP 'Location'
			URI instanceURI = uriInfo.getAbsolutePathBuilder().path(user.getLogin()).build();
			return Response.created(instanceURI).build();
		}
	}

	/**
	 * Method prenant en charge les requêtes HTTP GET.
	 *
	 * @return Une liste d'utilisateurs
	 * @throws SQLException 
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<User> getUsers() throws SQLException {
		return userDao.findAll();
	}

	/** 
	 * Méthode prenant en charge les requêtes HTTP GET sur /users/{login}
	 *
	 * @return Une instance de User ou l'erreur 404 si le User n'a pas été trouvé
	 * @throws SQLException 
	 */
	@GET
	@Path("{login}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User getUser(@PathParam("login") String login) throws SQLException {
		User user = userDao.find(login);
		if (user == null) {
			throw new NotFoundException();
		}
		return user;
	}

	/** 
	 * Méthode prenant en charge les requêtes HTTP DELETE sur /users/{login}
	 *
	 * @param login le nom de l'utilisateur
	 * @return La réponse de suppresion
	 * @throws SQLException 
	 */
	@DELETE
	@Path("{login}")
	public Response deleteUser(@PathParam("login") String login) throws SQLException {
		User user = userDao.find(login);
		// Si l'utilisateur est inconnu, on renvoie 404
		if (user == null) {
			throw new NotFoundException();
		}
		else {
			userDao.deleteById(login);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}

	/** 
	 * Méthode prenant en charge les requêtes HTTP PUT sur /users/{login}
	 *
	 * @param login le login de l'utilisateur à modifier
	 * @param user l'entité correspondant à la nouvelle instance
	 * @return Un code de retour HTTP dans un objet Response
	 * @throws SQLException 
	 */
	@PUT
	@Path("{login}")
	public Response updateUser(@PathParam("login") String login, User user) throws SQLException {
		// Si l'utilisateur est inconnu, on renvoie 404
		if (userDao.find(login) == null) {
			throw new NotFoundException();
		}
		else {
			userDao.update(user);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}

	/**
	 * Méthode de création d'un utilisateur qui prend en charge les requêtes HTTP POST au format application/x-www-form-urlencoded
	 * La méthode renvoie l'URI de la nouvelle instance en cas de succès
	 *
	 * @param login login de l'utilisateur
	 * @param password mdp de l'utilisateur
	 * @param firstName nom de l'utilisateur
	 * @param lastName prenom de l'utilisateur
	 * @param phoneNumber date de naissance de l'utilisateur
	 * @param email le mail de l'utilisateur
	 * @return Response le corps de la réponse est vide, le code de retour HTTP est fixé à 201 si la création est faite
	 *         L'en-tête contient un champs Location avec l'URI de la nouvelle ressource
	 * @throws SQLException 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createUser(@FormParam("company") String company, @FormParam("group") String group,
			@FormParam("login") String login, @FormParam("password") String password,
			@FormParam("firstName") String firstName, @FormParam("lastName") String lastName,
			@FormParam("phoneNumber") String phoneNumber, @FormParam("email") String email) throws SQLException {
		// Si l'utilisateur existe déjà, renvoyer 409
		if (userDao.find(login) != null) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		else {
			User user = new User(company, group, login, password, firstName, lastName, phoneNumber, email);
			userDao.insert(user);
			// On renvoie 201 et l'instance de la ressource dans le Header HTTP 'Location'
			URI instanceURI = uriInfo.getAbsolutePathBuilder().path(login).build();
			return Response.created(instanceURI).build();
		}
	}
	
	/**
	 * Méthode de récupération d'un utilisateur qui prend en charge les requêtes HTTP POST au format application/x-www-form-urlencoded
	 * La méthode renvoie l'utilisateur recherché en cas de succès, 404 sinon
	 *
	 * @param login login de l'utilisateur
	 * @param password mdp de l'utilisateur
	 * @return Une instance de User
	 * @throws SQLException 
	 */
	@POST
	@Path("{login}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User getUser(@PathParam("login") String login, @FormParam("password") String password) throws SQLException {
		User user = userDao.findByLogin(login, password);
		if (user == null) {
			throw new NotFoundException();
		}
		return user;
	}

}
