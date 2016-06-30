package racom.be.service.template;

import java.util.Map;

public interface TemplateService {
	public String compileTemplate(String name, Map<String, Object> data);

	public String compileTemplate(String name);
}
