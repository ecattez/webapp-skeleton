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
package fr.ecattez.mvc.standard;

import javax.ws.rs.ConstrainedTo;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.jersey.server.mvc.MvcFeature;

/**
 * {@link Feature} used to add support for {@link MvcFeature MVC} and Thymeleaf templates.
 * 
 * Note: This feature also registers {@link MvcFeature}.
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
