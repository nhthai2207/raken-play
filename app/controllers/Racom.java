package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import play.api.templates.Html;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import racom.be.dal.UserDao;
import racom.be.model.User;
import racom.be.service.AuthenService;
import racom.be.service.CustomStormPathRequest;
import racom.be.service.PlayStormPathRequest;
import racom.be.service.ServiceUtils;
import racom.be.service.template.TemplateService;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.http.HttpMethod;

@org.springframework.stereotype.Controller
public class Racom extends Controller {

	@Autowired
	@Qualifier("localTemplateServiceImpl")
	TemplateService templateService;

	@Autowired
	UserDao userDao;

	@Autowired
	@Qualifier("authenService")
	AuthenService authenService;

	public Result test() {
		System.out.println("On Controller");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("size", "Just notice that system run OK");
		return ok(Html.apply(templateService.compileTemplate("Racom/test.html", params)));
	}

	@OAuth2
	public Result testOAuth2() {
		ObjectNode result = Json.newObject();
		result.put(Application.STATUS, OK);
		result.put(Application.MESSAGE, "Valid request");
		return ok(result);
	}

	public Result regist() {
		JSONObject asJson = new JSONObject(request().body().asText());
		User user = new User(asJson);
		ObjectNode result = Json.newObject();
		Account account = authenService.createAccount(user);
		if (account != null) {
			result.put(Application.STATUS, OK);
			result.put(Application.MESSAGE, account.toString());
		} else {
			result.put(Application.STATUS, Application.NG);
			result.put(Application.MESSAGE, "Invalid data");
		}
		return ok(result);
	}

	public Result getOAuthTokenByUserPass() {
		JSONObject asJson = new JSONObject(request().body().asText());
		String userName = asJson.getString("userName");
		String password = asJson.getString("password");
		List<String> keys = authenService.getKey(userName, password);
		ObjectNode result = Json.newObject();
		if (keys != null && keys.size() > 0) {
			if (keys != null && keys.size() > 0) {
				String base64 = Base64.encodeBase64String(keys.get(0).getBytes());
				Map<String, String[]> headers = new LinkedHashMap<String, String[]>();
				headers.put("Authorization", new String [] {"Basic " + base64});
				headers.put("Content-Type", new String [] {"application/x-www-form-urlencoded"});				
				CustomStormPathRequest customStormPathRequest = new CustomStormPathRequest(headers, HttpMethod.POST);
				String exchangeToken = authenService.exchangeToken(customStormPathRequest);
				if (!ServiceUtils.isEmptyString(exchangeToken)) {
					result.put(Application.STATUS, Application.OK);
					result.put(Application.MESSAGE, exchangeToken);
				} else {
					result.put(Application.STATUS, Application.NG);
					result.put(Application.MESSAGE, "Cannot get Token for this account");
				}
				return ok(result);
			} else {
				// TODO Error
			}

			result.put(Application.MESSAGE, keys.toString());

		} else {
			result.put(Application.STATUS, Application.NG);
			result.put(Application.MESSAGE, "Cannot find key of this account");
		}
		return ok(result);
	}

	public Result getKey() {
		JSONObject asJson = new JSONObject(request().body().asText());
		String userName = asJson.getString("userName");
		String password = asJson.getString("password");
		List<String> keys = authenService.getKey(userName, password);
		String encryptType = asJson.getString("encryptType");
		ObjectNode result = Json.newObject();
		if (keys != null && keys.size() > 0) {
			result.put(Application.STATUS, Application.OK);
			if (!ServiceUtils.isEmptyString(encryptType) && "base64".equals(encryptType)) {
				List<String> encryptList = new ArrayList<String>();
				for (String key : keys) {
					String base64 = Base64.encodeBase64String(key.getBytes());
					encryptList.add(base64);
				}
				result.put(Application.MESSAGE, encryptList.toString());
			} else {
				result.put(Application.MESSAGE, keys.toString());
			}
		} else {
			result.put(Application.STATUS, Application.NG);
			result.put(Application.MESSAGE, "Cannot find key of this account");
		}
		return ok(result);
	}

	public Result basicAuthen() {
		boolean basicAuthen = authenService.basicAuthen(new PlayStormPathRequest(request()));
		ObjectNode result = Json.newObject();
		if (basicAuthen) {
			result.put(Application.STATUS, Application.OK);
			result.put(Application.MESSAGE, "Valid Authen");
		} else {
			result.put(Application.STATUS, Application.NG);
			result.put(Application.MESSAGE, "Invalid Authen");
		}
		return ok(result);

	}

	public Result getOAuthTokenByKey() {
		String exchangeToken = authenService.exchangeToken(new PlayStormPathRequest(request()));
		ObjectNode result = Json.newObject();
		if (!ServiceUtils.isEmptyString(exchangeToken)) {
			result.put(Application.STATUS, Application.OK);
			result.put(Application.MESSAGE, exchangeToken);
		} else {
			result.put(Application.STATUS, Application.NG);
			result.put(Application.MESSAGE, "Cannot get Token for this account");
		}
		return ok(result);
	}

	public Result oAuth() {
		boolean oAuthAuthen = authenService.oAuthAuthen(new PlayStormPathRequest(request()));
		ObjectNode result = Json.newObject();
		if (oAuthAuthen) {
			result.put(Application.STATUS, Application.OK);
			result.put(Application.MESSAGE, "Valid Token");
		} else {
			result.put(Application.STATUS, Application.NG);
			result.put(Application.MESSAGE, "Invalid Token");
		}
		return ok(result);
	}

}
