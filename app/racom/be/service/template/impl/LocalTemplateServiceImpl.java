package racom.be.service.template.impl;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import play.Play;
import racom.be.service.template.AbstractTemplateService;
import racom.be.service.template.TemplateService;

@Service("localTemplateServiceImpl")
public class LocalTemplateServiceImpl extends AbstractTemplateService implements TemplateService{

	@Autowired
	@Override
	public void setResourceLoader(@Qualifier("localTemplateResourceLoader") ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public void initVelocityEngine() {
		engine = new VelocityEngine();
		engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "play");
		engine.setProperty("play.resource.loader.instance", resourceLoader);
		engine.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
		engine.setProperty(Velocity.INPUT_ENCODING, "utf-8");

		engine.setProperty("play.resource.loader.path", Play.application().path()+"/veloviews/");
		//Velocity.addProperty("template.resource.loader.class", TemplateResourceLoader.class.getName());
		engine.setProperty("play.resource.loader.class", resourceLoader.getClass().getName());
		engine.setProperty("play.resource.loader.cache", true);
		engine.setProperty("play.resource.loader.modificationCheckInterval", "10");
		engine.setProperty("velocimacro.library", "");
		//engine.setProperty("userdirective", "com.gnt.operation.template.infrastructure.velocity.ImageDirective");
		engine.init();
		
	}

	@Override
	public void initVelocityContext() {
		/*Initialize default context*/
		defaultContext = new VelocityContext();
		defaultContext.put("title", "Welcome to GNT");
		
	}

	
}
