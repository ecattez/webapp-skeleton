package fr.ecattez.resource;

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

import fr.ecattez.entity.deprecated.Company;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CompanyResourceTestCase extends ResourceTest {
	
	public CompanyResourceTestCase() {
		super("/companies");
	}

	@Test
	public void test_A_GetEmptyListofCompanies() {
		List<Company> list = target(URI_PATH).request().get(new GenericType<List<Company>>(){});
		assertTrue(list.isEmpty());
	}

	@Test
	public void test_B_CreateCompany() {
		Company company = new Company("leaderinfo", "LEADER Informatique", "33 rue Charles Muyssart", "", "59000");
		Entity<Company> companyEntity = Entity.entity(company, MediaType.APPLICATION_JSON);
		
		Response response = target(URI_PATH).request().post(companyEntity);
		assertEquals(201, response.getStatus());

		URI uriAttendue = target(URI_PATH).path(company.getCompanyId()).getUri();
		assertTrue(uriAttendue.equals(response.getLocation()));
	}

	@Test
	public void test_C_CreateSameCompany() {
		Company company = new Company("leaderinfo", "LEADER Informatique", "33 rue Charles Muyssart", "", "59000");
		Entity<Company> companyEntity = Entity.entity(company, MediaType.APPLICATION_JSON);
		int same = target(URI_PATH).request().post(companyEntity).getStatus();
		assertEquals(409, same);
	}

	@Test
	public void test_D_GetTwoCompany() {
		Company company = new Company("antenia", "ANTENIA", "8 Rue Halévy", "", "75009");
		Entity<Company> companyEntity = Entity.entity(company, MediaType.APPLICATION_JSON);
		target(URI_PATH).request().post(companyEntity);
		List<Company> list = target(URI_PATH).request().get(new GenericType<List<Company>>(){});
		assertEquals(2, list.size());
	}

	@Test
	public void test_E_GetOneCompany() {
		Company company = new Company("leaderinfo", "LEADER Informatique", "33 rue Charles Muyssart", "", "59000");
		Company result = target(URI_PATH).path("leaderinfo").request().get(Company.class);
		assertEquals(company, result);
	}

	@Test
	public void test_F_GetInexistantCompany() {
		int notFound = target(URI_PATH).path("la7prod").request().get().getStatus();
		assertEquals(404, notFound);
	}

	@Test
	public void test_G_DeleteOneCompany() {
		int code = target(URI_PATH).path("antenia").request().delete().getStatus();
		assertEquals(204, code);
		int notFound = target(URI_PATH).path("antenia").request().get().getStatus();
		assertEquals(404, notFound);
	}
 
	@Test
	public void test_H_DeleteInexistantCompany() {
		int notFound = target(URI_PATH).path("la7prod").request().delete().getStatus();
		assertEquals(404, notFound);
	}

	@Test
	public void test_I_ModifyCompany() {
		Company modified = new Company("leaderinfo", "LEADER Informatique", "33 rue Charles Muyssart", "", "59000");
		Entity<Company> companyEntity = Entity.entity(modified, MediaType.APPLICATION_JSON); 
		int noContent = target(URI_PATH).path("leaderinfo").request().put(companyEntity).getStatus();
		assertEquals(204, noContent);
		Company retrieved = target(URI_PATH).path("leaderinfo").request().get(Company.class);
		assertEquals(modified, retrieved);
	}
 
	@Test
	public void test_J_ModifyInexistantCompany() {
		Company inexistant = new Company("antenia", "ANTENIA", "8 Rue Halévy", "", "75009");
		Entity<Company> companyEntity = Entity.entity(inexistant, MediaType.APPLICATION_JSON);
		int notFound = target(URI_PATH).path("jsteed").request().put(companyEntity).getStatus();
		assertEquals(404, notFound);
	}
	
}
