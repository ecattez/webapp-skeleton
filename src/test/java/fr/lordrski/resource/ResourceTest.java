package fr.lordrski.resource;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;

import fr.lordrski.App;

/**
 * ResourceTest est étendu par tous les junit qui ont besoin de {@link fr.lordrski.App} pour fonctionner.
 * Ces junit permettent généralement de tester les ressources et leurs services.
 */
public class ResourceTest extends JerseyTest {
	
	protected final String URI_PATH;
	
	public ResourceTest(final String PATH) {
		this.URI_PATH = PATH;
	}

	/**
	* Il est obligatoire de redéfinir cette méthode qui permet de configurer le contexte de Jersey
	*/
	@Override
	protected Application configure() {
		return new App();
	}
	
	/**
	 * Retourne l'uri de la ressource
	 * 
	 * @return l'uri de la ressource
	 */
	public final String uriPath() {
		return this.URI_PATH;
	}

}
