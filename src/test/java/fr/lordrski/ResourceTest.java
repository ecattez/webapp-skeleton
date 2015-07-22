package fr.lordrski;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;

/**
 * ResourceTest est étendu par tous les junit qui ont besoin de {@link fr.lordrski.App} pour fonctionner.
 * Ces junit permettent généralement de tester les ressources et leurs services.
 */
public class ResourceTest extends JerseyTest {

	/**
	* Il est obligatoire de redéfinir cette méthode qui permet de configurer le contexte de Jersey
	*/
	@Override
	protected Application configure() {
		return new App();
	}

}
