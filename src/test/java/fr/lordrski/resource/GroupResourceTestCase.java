package fr.lordrski.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.lordrski.entity.deprecated.Group;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GroupResourceTestCase extends ResourceTest {
	
	private static String company = "leaderinfo";
	
	public GroupResourceTestCase() {
		super("/groups");
	}

	@Test
	public void test_A_GetEmptyListofGroups() {
		List<Group> list = target(URI_PATH).request().get(new GenericType<List<Group>>(){});
		assertTrue(list.isEmpty());
	}

	@Test
	public void test_B_CreateGroup() {
		Group group = new Group(company, "admin", "Administrateur");
		Entity<Group> groupEntity = Entity.entity(group, MediaType.APPLICATION_JSON);
		
		Response response = target(URI_PATH).request().post(groupEntity);
		assertEquals(201, response.getStatus());

		URI uriAttendue = target(URI_PATH).path(group.getGroupId()).getUri();
		assertTrue(uriAttendue.equals(response.getLocation()));
	}

	@Test
	public void test_C_CreateSameGroup() {
		Group group = new Group(company, "admin", "Administrateur");
		Entity<Group> groupEntity = Entity.entity(group, MediaType.APPLICATION_JSON);
		int same = target(URI_PATH).request().post(groupEntity).getStatus();
		assertEquals(409, same);
	}

	@Test
	public void test_D_GetTwoGroup() {
		Group group = new Group(company, "user", "Utilisateur");
		Entity<Group> groupEntity = Entity.entity(group, MediaType.APPLICATION_JSON);
		target(URI_PATH).request().post(groupEntity);
		List<Group> list = target(URI_PATH).request().get(new GenericType<List<Group>>(){});
		assertEquals(2, list.size());
	}

	@Test
	public void test_E_GetOneGroup() {
		Group group = new Group(company, "user", "Utilisateur");
		Group result = target(URI_PATH).path("user").request().get(Group.class);
		assertEquals(group, result);
	}

	@Test
	public void test_F_GetInexistantGroup() {
		int notFound = target(URI_PATH).path("dev").request().get().getStatus();
		assertEquals(404, notFound);
	}

	@Test
	public void test_G_DeleteOneGroup() {
		int code = target(URI_PATH).path("user").request().delete().getStatus();
		assertEquals(204, code);
		int notFound = target(URI_PATH).path("user").request().get().getStatus();
		assertEquals(404, notFound);
	}
 
	@Test
	public void test_H_DeleteInexistantGroup() {
		int notFound = target(URI_PATH).path("dev").request().delete().getStatus();
		assertEquals(404, notFound);
	}

	@Test
	public void test_I_ModifyGroup() {
		Group modified = new Group(company, "admin", "Administrateur");
		Entity<Group> groupEntity = Entity.entity(modified, MediaType.APPLICATION_JSON); 
		int noContent = target(URI_PATH).path("admin").request().put(groupEntity).getStatus();
		assertEquals(204, noContent);
		Group retrieved = target(URI_PATH).path("admin").request().get(Group.class);
		assertEquals(modified, retrieved);
	}
 
	@Test
	public void test_J_ModifyInexistantGroup() {
		Group inexistant = new Group(company, "user", "Utilisateur");
		Entity<Group> groupEntity = Entity.entity(inexistant, MediaType.APPLICATION_JSON);
		int notFound = target(URI_PATH).path("user").request().put(groupEntity).getStatus();
		assertEquals(404, notFound);
	}
	
}
