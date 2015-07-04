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
package fr.lordrski.mvc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.spi.TemplateProcessor;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.StandardTemplateModeHandlers;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * {@link TemplateProcessor} used to add support for Thymeleaf templates
 */
@Provider
public class ThymeleafProcessor implements TemplateProcessor<String> {
	
	private static final String UTF_8 = "UTF-8";

	@Context
	private ServletContext servletContext;
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;
	
	private TemplateEngine templateEngine;
	
	public ThymeleafProcessor() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setTemplateMode(StandardTemplateModeHandlers.HTML5.getTemplateModeName());
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setCacheTTLMs(1000L);
		
		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
	}

	@Override
	public String resolve(String templatePath, MediaType mediaType) {
		return templatePath;
	}

	@Override
	public void writeTo(String templateReference, Viewable viewable, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream out) throws IOException {
		try (OutputStreamWriter writer = new OutputStreamWriter(out, UTF_8)) {
			WebContext context = new WebContext(request, response, servletContext, request.getLocale());
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("it", viewable.getModel());
			context.setVariables(variables);
			templateEngine.process(viewable.getTemplateName(), context, writer);
		}		
	}

}
