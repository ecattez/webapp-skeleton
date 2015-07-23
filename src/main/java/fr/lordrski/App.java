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
package fr.lordrski;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.skife.jdbi.v2.Handle;

import fr.lordrski.mvc.ThymeleafMvcFeature;
import fr.lordrski.tool.JdbiTool;
import fr.lordrski.util.CORSResponseFilter;
import fr.lordrski.util.Folder;
import fr.lordrski.util.ScriptRunner;

/**
 * {@link javax.ws.rs.core.Application} charge toutes les ressources de l'application.
 */
public class App extends ResourceConfig {
	
	public App() {
		register(LoggingFilter.class);
		register(ThymeleafMvcFeature.class);
		register(MultiPartFeature.class);
		register(CORSResponseFilter.class);
		packages("fr.lordrski.resources");
		runSQL();
	}
	
	public App(@Context ServletContext context) {
		this();
		initializeContext(context);
	}
	
	/**
	 * Initialise le ServletContext au lancement de l'application
	 * @param context le ServletContext
	 */
	private void initializeContext(ServletContext context) {
		final String root = context.getContextPath();
		context.setAttribute("css", root + "/css/");
		context.setAttribute("default_css", root + "/css/style.css");
		context.setAttribute("js", root + "/js/");
		context.setAttribute("jQuery", root + "/js/jquery-2.1.4.min.js");
	}
	
	/**
	 * Exécute tous les fichiers SQL contenu dans le dossier de configuration de l'application
	 */
	private void runSQL() {
		File folder = Folder.CONFIG.toPath().toFile();
		if (folder.isDirectory()) {
			Arrays.stream(folder.listFiles()).filter(f -> f.getName().endsWith(".sql")).forEach(f -> runScript(f));
		}
		else {
			folder.mkdirs();
		}
	}
	
	/**
	 * Exécute le fichier SQL passé en paramètre
	 * @param file le fichier SQL à exécuter
	 */
	private void runScript(File file) {
		Handle handle = JdbiTool.getDBI().open();
		ScriptRunner script = new ScriptRunner(handle.getConnection(), false ,false);
		try {
			script.runScript(new FileReader(file));
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		handle.close();
	}

}
