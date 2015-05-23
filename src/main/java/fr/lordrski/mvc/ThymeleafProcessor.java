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
 *
 * @author Edouard CATTEZ (la7production)
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
