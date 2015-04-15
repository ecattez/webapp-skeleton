package fr.lordrski.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

public class Configuration {
	
	public static final String FILENAME = "config.properties";
	
	private Properties properties;
	
	public Configuration() {
		this.properties = new Properties();
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public Object setProperty(String key, String value) {
		return properties.setProperty(key, value);
	}
	
	public void create() {
		Writer out = null;
		try {
			out = new PrintWriter(FILENAME);
			properties.setProperty("driver", "jdbc:sqlite");
			properties.setProperty("database", "data.db");
			properties.store(out, null);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void load() {
		Reader in = null;
		Writer out = null;
		
		if (!new File(FILENAME).isFile())
			create();
		else {
			try {
				in = new FileReader(FILENAME);
				out = new PrintWriter(FILENAME);
				properties.load(in);
				
				if (!properties.containsKey("driver"))
					properties.setProperty("driver", "jdbc:sqlite");
				if (!properties.containsKey("database"))
					properties.setProperty("database", "data.db");
				properties.store(out, null);
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				try {
					in.close();
					out.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
