/**
 * 
 */
package racom.be.config.spring;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.StringUtils;


public class PlayPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	
	@Override
	protected String resolvePlaceholder(String placeholder, Properties props) {
		try {
			String result = play.Play.application().configuration().getString(placeholder);
			if (StringUtils.hasText(result)) {
				return result;
			}
			return super.resolvePlaceholder(placeholder, props);
		} catch (Exception e) {
			// Threre is no play application started. (In case unit test)
			return super.resolvePlaceholder(placeholder, props);
		}
	}


}
