package racom.be.service;

import java.util.Map;

import play.mvc.Http.Request;

import com.stormpath.sdk.http.HttpMethod;
import com.stormpath.sdk.http.HttpRequest;

public class PlayStormPathRequest implements HttpRequest {
	private Request playRequest;

	public PlayStormPathRequest(Request playRequest) {
		this.playRequest = playRequest;
	}

	@Override
	public String getHeader(String arg0) {
		return this.playRequest.getHeader(arg0);
	}

	@Override
	public Map<String, String[]> getHeaders() {
		return this.playRequest.headers();
	}

	@Override
	public HttpMethod getMethod() {
		return HttpMethod.fromName(playRequest.method());
	}

	@Override
	public String getParameter(String arg0) {
		String[] strings = this.getParameters().get(arg0);
		return (strings != null && strings.length > 0) ? strings[0] : "";
	}

	@Override
	public Map<String, String[]> getParameters() {
		if (playRequest.method().equals("GET")) {
			return playRequest.queryString();
		} else {
			return playRequest.body().asFormUrlEncoded();
		}
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
