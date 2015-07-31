package fr.lordrski.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.lordrski.entity.fictable.Fictable;
import fr.lordrski.entity.fictable.FictableEntry;
import fr.lordrski.entity.fictable.FictableInfos;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FictableResourceTestCase extends ResourceTest {

	private static final Fictable FICTABLE = new Fictable("clients.json", "Fictable d'exemple");
	
	public FictableResourceTestCase() {
		super("/fictables");
	}
	
	@BeforeClass
	public static void initialize() {
		List<FictableInfos> columns = new ArrayList<>();
		List<FictableEntry> entries = new ArrayList<>();
		
		columns.add(new FictableInfos("idCompany", "Company", "string", 20));
		columns.add(new FictableInfos("idClient", "Client", "string", 20));
		columns.add(new FictableInfos("firstName", "Firstname", "string", 20));
		columns.add(new FictableInfos("lastName", "Lastname", "string", 20));
		columns.add(new FictableInfos("address", "Address", "string", 20));
		columns.add(new FictableInfos("email", "Email", "string", 20));
		
		FictableEntry tmp;
		for (int i=0; i<2000; i++) {
			tmp = new FictableEntry();
			tmp.put("idCompany", "leaderinfo");
			tmp.put("idClient", "client" + i);
			tmp.put("firstName", "Jean " + i);
			tmp.put("lastName", "Alphonse " + i);
			tmp.put("address", "33 rue Charles " + i);
			tmp.put("email", "jeanalphonse" + i + "@gmail.com");
			entries.add(tmp);
		}
		
		FICTABLE.setInfos(columns);
		FICTABLE.setEntries(entries);
	}
	
	@Test
	public void test_A_GetInexistantFictable() {
		int notFound = target(URI_PATH).path(FICTABLE.getFileName()).request().get().getStatus();
		assertEquals(404, notFound);
	}
	
	@Test
	public void test_B_CreateFictable() {
		Entity<Fictable> fictableEntity = Entity.entity(FICTABLE, MediaType.APPLICATION_JSON);
		Response response = target(URI_PATH).request().post(fictableEntity);
		assertEquals(201, response.getStatus());
		URI uriAttendue = target(URI_PATH).path(FICTABLE.getFileName()).getUri();
		assertTrue(uriAttendue.equals(response.getLocation()));
	}
	
	@Test
	public void test_C_CreateSameFictable() {
		Entity<Fictable> fictableEntity = Entity.entity(FICTABLE, MediaType.APPLICATION_JSON);
		int same = target(URI_PATH).request().post(fictableEntity).getStatus();
		assertEquals(409, same);
	}
	
	@Test
	public void test_D_DeleteFictable() {
		int same = target(URI_PATH).path(FICTABLE.getFileName()).request().delete().getStatus();
		assertEquals(204, same);
		int notFound = target(URI_PATH).path(FICTABLE.getFileName()).request().get().getStatus();
		assertEquals(404, notFound);
	}
	
	@Test
	public void test_H_DeleteInexistantFictable() {
		int notFound = target(URI_PATH).path(FICTABLE.getFileName()).request().delete().getStatus();
		assertEquals(404, notFound);
	}
	
	@Test
	public void test_I_ModifyFictable() {
		Entity<Fictable> fictableEntity = Entity.entity(FICTABLE, MediaType.APPLICATION_JSON);
		target(URI_PATH).request().post(fictableEntity);
		Fictable modified = FICTABLE.clone();
		modified.setDisplayName("Fictable d'exemple modifiée");
		fictableEntity = Entity.entity(modified, MediaType.APPLICATION_JSON); 
		int noContent = target(URI_PATH).path(FICTABLE.getFileName()).request().put(fictableEntity).getStatus();
		assertEquals(204, noContent);
		Fictable retrieved = target(URI_PATH).path(FICTABLE.getFileName()).request().get(Fictable.class);
		assertEquals(modified, retrieved);
	}
 
	/**
	*
	* Vérifie que la suppression d'un utilisateur inexistant renvoie 404
	*/
	@Test
	public void test_J_ModifyInexistantFictable() {
		Fictable inexistant = new Fictable("test.json", "Test");
		Entity<Fictable> fictableEntity = Entity.entity(inexistant, MediaType.APPLICATION_JSON);
		int notFound = target(URI_PATH).path(inexistant.getFileName()).request().put(fictableEntity).getStatus();
		assertEquals(404, notFound);
	}

}
