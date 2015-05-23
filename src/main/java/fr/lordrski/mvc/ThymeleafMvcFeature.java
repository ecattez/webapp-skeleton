package fr.lordrski.mvc;

import javax.ws.rs.ConstrainedTo;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.jersey.server.mvc.MvcFeature;

/**
 * {@link Feature} used to add support for {@link MvcFeature MVC} and Thymeleaf templates
 * <p/>
 * Note: This feature also registers {@link MvcFeature}.
 *
 * @author Edouard CATTEZ (la7production)
 */
@ConstrainedTo(RuntimeType.SERVER)
public class ThymeleafMvcFeature implements Feature {
	
	@Override
	public boolean configure(final FeatureContext context) {
		final Configuration config = context.getConfiguration();
		
		if (!config.isRegistered(ThymeleafProcessor.class)) {
			// Template Processor
			context.register(ThymeleafProcessor.class);
			
			// MvcFeature
			if (!config.isRegistered(MvcFeature.class)) {
				context.register(MvcFeature.class);
			}
			return true;
		}
		return false;
	}
}
