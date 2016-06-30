package racom.be.velocity.loader;

import java.io.InputStream;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;
import org.springframework.stereotype.Component;
@Component("localTemplateResourceLoader")
public class LocalTemplateResourceLoader extends FileResourceLoader{

	@Override
	public InputStream getResourceStream(String name)
			throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return super.getResourceStream(name);
	}

	@Override
	public boolean isSourceModified(Resource name) {
		// TODO Auto-generated method stub
		return super.isSourceModified(name);
	}

	
}
