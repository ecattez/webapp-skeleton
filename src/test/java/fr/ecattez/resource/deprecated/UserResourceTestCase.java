package fr.ecattez.resource.deprecated;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.ecattez.entity.deprecated.User;
import fr.ecattez.resource.ResourceTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserResourceTestCase extends ResourceTest {
	
	private static String company = "leaderinfo";
	private static String group = "admin";
	
	public UserResourceTestCase() {
		super("/users");
	}

	/**
	 * Vérifie qu'initialement on a une liste d'utilisateurs vide
	 */
	@Test
	public void test_A_GetEmptyListofUsers() {
		List<User> list = target(URI_PATH).request().get(new GenericType<List<User>>(){});
		assertTrue(list.isEmpty());
	}

	/**
	 * Test de création d'un utilisateur (retour HTTP et envoi de l'URI de la nouvelle instance)
	 */
	@Test
	public void test_B_CreateUser() {
		User user = new User(company, group, "jsteed", "secret", "Steed", "John", "0606060606", "jsteed@mi5.uk");
		
		// Conversion de l'instance de User au format JSON pour l'envoi
		Entity<User> userEntity = Entity.entity(user, MediaType.APPLICATION_JSON);

		// Envoi de la requête HTTP POST pour la création de l'utilisateur
		Response response = target(URI_PATH).request().post(userEntity);

		// Vérification du code de retour HTTP
		assertEquals(201, response.getStatus());

		// Vérification que la création renvoie bien l'URI de la nouvelle instance dans le header HTTP 'Location'
		// ici : http://localhost:8080/usersdb/jsteed
		URI uriAttendue = target(URI_PATH).path(user.getLogin()).getUri();
		assertTrue(uriAttendue.equals(response.getLocation()));
	}

	/**
	 * Test de création en double d'un utilisateur. Doit renvoyer 409
	 * ! Cela fonctionne car le test précédent à déjà créé l'utilisateur et que le container est conservé !
	 */
	@Test
	public void test_C_CreateSameUser() {
		User user = new User(company, group, "jsteed", "secret", "Steed", "John", "0606060606", "jsteed@mi5.uk");
		Entity<User> userEntity = Entity.entity(user, MediaType.APPLICATION_JSON);
		int same = target(URI_PATH).request().post(userEntity).getStatus();
		assertEquals(409, same);
	}

	/**
	 * Vérifie que je renvoie bien une liste contenant tous les utilisateurs (ici 2)
	 */
	@Test
	public void test_D_GetTwoUsers() {
		User user = new User(company, group, "epeel", "magic", "Peel", "Edgard", "0606060606", "epeel@mi5.uk");
		Entity<User> userEntity = Entity.entity(user, MediaType.APPLICATION_JSON);
		target(URI_PATH).request().post(userEntity);
		List<User> list = target(URI_PATH).request().get(new GenericType<List<User>>(){});
		assertEquals(2, list.size());
	}

	/**
	 * Vérifie la récupération d'un utilisateur spécifique en GET
	 */
	@Test
	public void test_E_GetOneUser_Get() {
		User user = new User(company, group, "jsteed", "secret", "Steed", "John", "0606060606", "jsteed@mi5.uk");
		User result = target(URI_PATH).path("jsteed").request().get(User.class);
		assertEquals(user, result);
	}

	/**
	 * Vérifie que la récupération d'un utilisateur inexistant renvoie 404
	 */
	@Test
	public void test_F_GetInexistantUser() {
		int notFound = target(URI_PATH).path("tking").request().get().getStatus();
		assertEquals(404, notFound);
	}

	/**
	 * Vérifie que la suppression d'une ressource est effective
	 */
	@Test
	public void test_G_DeleteOneUser() {
		int code = target(URI_PATH).path("jsteed").request().delete().getStatus();
		assertEquals(204, code);
		int notFound = target(URI_PATH).path("jsteed").request().get().getStatus();
		assertEquals(404, notFound);
	}
 
	/**
	 * Vérifie que la suppression d'un utilisateur inexistant renvoie 404
	 */
	@Test
	public void test_H_DeleteInexistantUser() {
		int notFound = target(URI_PATH).path("tking").request().delete().getStatus();
		assertEquals(404, notFound);
	}

	/**
	 * Vérifie que la modification d'une ressource est effective
	 */
	@Test
	public void test_I_ModifyUser() {
		User modified = new User(company, group, "epeel", "magic", "Peel", "Edgard", "0606060606", "epeel@cia.usa");
		Entity<User> userEntity = Entity.entity(modified, MediaType.APPLICATION_JSON); 
		int noContent = target(URI_PATH).path("epeel").request().put(userEntity).getStatus();
		assertEquals(204, noContent);
		User retrieved = target(URI_PATH).path("epeel").request().get(User.class);
		assertEquals(modified, retrieved);
	}
 
	/**
	 * Vérifie que la suppression d'un utilisateur inexistant renvoie 404
	 */
	@Test
	public void test_J_ModifyInexistantUser() {
		User inexistant = new User(company, group, "jsteed", "secret", "Steed", "John", "0606060606", "jsteed@mi5.uk");
		Entity<User> userEntity = Entity.entity(inexistant, MediaType.APPLICATION_JSON);
		int notFound = target(URI_PATH).path("jsteed").request().put(userEntity).getStatus();
		assertEquals(404, notFound);
	}


	@Test
	public void test_K_CreateUserFromForm() {
		Form form = new Form();
		form.param("company", company);
		form.param("group", group);
		form.param("login", "tking");
		form.param("password", "iloveburgers");
		form.param("firstName", "King");
		form.param("lastName", "Terry");
		form.param("phoneNumber","0808080808");
		form.param("email", "tking@mi5.uk");

		Entity<Form> formEntity = Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE);
		int code = target(URI_PATH).request().post(formEntity).getStatus(); 
		assertEquals(201, code);
	}
	
	/**
	 * Vérifie la récupération d'un utilisateur spécifique en POST
	 */
	@Test
	public void test_L_GetUserFromForm() {
		Form form = new Form();
		form.param("password", "magic");
		
		User user = new User(company, group, "epeel", "magic", "Peel", "Edgard", "0606060606", "epeel@mi5.uk");
		Entity<Form> userEntity = Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE);
		User result = target(URI_PATH).path("epeel").request().post(userEntity, User.class);
		assertEquals(user, result);
	}


	/**
	 * Vérifie qu'on récupère bien un utilisateur avec le type MIME application/xml
	 */
	@Test
	public void test_M_GetUserAsXml() { 
		int code = target(URI_PATH).path("tking").request(MediaType.APPLICATION_XML).get().getStatus();
		assertEquals(code, 200);
	}
	
}
