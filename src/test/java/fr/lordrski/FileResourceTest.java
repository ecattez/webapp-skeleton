package fr.lordrski;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.lordrski.util.Folder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileResourceTest extends ResourceTest {
	
	private static final String FILE_PATH = "/files";
	
	@Test
	public void test_A_Download() {
		System.out.println(Folder.EXCHANGE.resolve("test"));
		System.out.println(Folder.EXCHANGE.resolve(""));
	}
	
	@Test
	public void test_B_Upload() {
		
	}

}
