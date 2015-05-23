package fr.lordrski.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * {@link Properties} read from file
 * 
 * @author Edouard CATTEZ (la7production)
 */
public class PropertyFile extends Properties {

	private static final long serialVersionUID = 6157789020552647484L;
	
	private Path path;
	
	public PropertyFile(String uri, boolean load) {
		this.path = Paths.get(uri);
		if (load) {
			load();
		}
	}
	
	public PropertyFile(String uri) {
		this(uri, false);
	}
	
	public PropertyFile() {}
	
	public Path getPath() {
		return this.path;
	}
	
	public void setPath(Path path) {
		this.path = path;
	}
	
	public void create() {
		File file = path.toFile();
		if (!file.isFile()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void load() {
		this.create();
		this.reload();
	}
	
	private void reload() {
		FileReader reader = null;
		try {
			reader = new FileReader(path.toFile());
			this.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
