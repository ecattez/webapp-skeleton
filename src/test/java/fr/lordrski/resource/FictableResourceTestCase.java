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

	private static final Fictable FICTABLE = new Fictable("exemple.json", "Exemple");
	
	public FictableResourceTestCase() {
		super("/fictables");
	}
	
	@BeforeClass
	public static void initialize() {
		List<FictableInfos> columns = new ArrayList<>();
		List<FictableEntry> entries = new ArrayList<>();
		
		columns.add(new FictableInfos("id", "int", 5));
		columns.add(new FictableInfos("label", "string", 20));
		columns.add(new FictableInfos("activated", "boolean", 1));
		columns.add(new FictableInfos("owner", "string", 8));
		columns.add(new FictableInfos("permission", "string", 20));
		columns.add(new FictableInfos("couilleEnOr", "boolean", 1));
		
		FictableEntry tmp;
		for (int i=0; i<2000; i++) {
			tmp = new FictableEntry();
			tmp.put("id", "" + i);
			tmp.put("label", "label" + i);
			tmp.put("activated", "" + ((i%2 == 0) ? 1 : 0));
			tmp.put("owner", "Edouard");
			tmp.put("permission", "fictables.create");
			tmp.put("couilleEnOr", "1");
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
		modified.setDisplayName("Exemple modifie");
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
