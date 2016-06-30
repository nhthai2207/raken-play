package racom.be.service.template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.springframework.beans.factory.InitializingBean;


public abstract  class AbstractTemplateService implements TemplateService, InitializingBean{
	
	
	
	protected ResourceLoader resourceLoader;
	
	protected final String ENCODING_DEFAULT = "UTF-8";
	protected VelocityEngine engine;
	protected VelocityContext defaultContext;
	@Override
	public String compileTemplate(String name, Map<String, Object> data) {
		Template template = engine.getTemplate(name, ENCODING_DEFAULT);
		VelocityContext context = new VelocityContext(data, defaultContext);
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		//logger.info("Charset {}", Charset.defaultCharset());
		//logger.info("Result after compile:  {}", writer.toString());
		return writer.toString();
	}
	@Override
	public String compileTemplate(String name) {
		return compileTemplate(name, new HashMap<String, Object>());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initVelocityEngine();
		initVelocityContext();
	}
	
	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}
	
	public abstract void initVelocityEngine();
	public abstract void initVelocityContext();

	public abstract void setResourceLoader(ResourceLoader resourceLoader) ;
	
}
