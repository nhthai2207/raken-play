package racom.be.service;

import java.util.LinkedHashMap;
import java.util.Map;

import com.stormpath.sdk.http.HttpMethod;
import com.stormpath.sdk.http.HttpRequest;

public class CustomStormPathRequest implements HttpRequest {

	private Map<String, String[]> headers;
	private Map<String, String[]> queryString;
	private HttpMethod method;

	public CustomStormPathRequest(Map<String, String[]> headers, HttpMethod method) {
		this.queryString = new LinkedHashMap<String, String[]>();
		queryString.put("grant_type", new String[] { "client_credentials" });
		this.method = method;
		this.headers = headers;		
	}

	@Override
	public String getHeader(String arg0) {
		String[] strings = this.headers.get(arg0);
		if (strings != null && strings.length > 0) {
			return strings[0];
		}
		return null;
	}

	@Override
	public Map<String, String[]> getHeaders() {
		return this.headers;
	}

	@Override
	public HttpMethod getMethod() {
		return this.method;
	}

	@Override
	public String getParameter(String arg0) {
		String[] strings = this.getParameters().get(arg0);
		return (strings != null && strings.length > 0) ? strings[0] : "";
	}

	@Override
	public Map<String, String[]> getParameters() {
		return queryString;
	}

	@Override
	public String getQueryParameters() {
		StringBuilder ret = new StringBuilder();
		Map<String, String[]> parameters = getParameters();
		for (String key : parameters.keySet()) {
			String[] value2 = parameters.get(key);
			ret.append(String.format("&%s=%s", key, value2[0]));
		}
		return ret.length() > 0 ? ret.toString().substring(1) : "";
	}

}
