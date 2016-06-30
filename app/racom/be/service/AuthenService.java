package racom.be.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Service;

import play.api.http.MediaRange;
import play.api.http.MediaType;
import play.api.i18n.Lang;
import play.api.mvc.Cookies;
import play.api.mvc.Flash;
import play.api.mvc.Headers;
import play.api.mvc.RequestHeader;
import play.api.mvc.Session;
import play.mvc.Http;
import play.mvc.Http.Request;
import racom.be.model.User;
import scala.Option;
import scala.collection.Seq;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.api.ApiAuthenticationResult;
import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.api.ApiKeyList;
import com.stormpath.sdk.api.ApiKeys;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.application.ApplicationList;
import com.stormpath.sdk.application.Applications;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.authc.AuthenticationResultVisitor;
import com.stormpath.sdk.authc.UsernamePasswordRequest;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.Clients;
import com.stormpath.sdk.http.HttpRequest;
import com.stormpath.sdk.oauth.AccessTokenResult;
import com.stormpath.sdk.oauth.OauthAuthenticationResult;
import com.stormpath.sdk.oauth.TokenResponse;

@Service("authenService")
public class AuthenService {

	// From WebMobileApp // account root: david227000@gmail.com
	// private static String apiKey = "1F17OIZIOHQ7C69MR11U55SN8";
	// private static String secretKey =
	// "Wg6EvmGJyinKQ8z9O5As7K1iTVqlS9znVRbR2E+cBEE";

	// Wineta key // account root: geoffrey@wineta.com
	private static String apiKey = "6DBMMS9IJKGUXCHN66W1NWEB6";
	private static String secretKey = "bCRV753Bj7cj5JWtHtX7YCpq4/0Ija/JH5d6b5YWJLs";

	private Client client;
	private Application application;

	public AuthenService() {
		try {
			Properties properties = new Properties();
			properties.setProperty("apiKey.id", apiKey);
			properties.setProperty("apiKey.secret", secretKey);
			ApiKey apiKey = ApiKeys.builder().setProperties(properties).build();
			client = Clients.builder().setApiKey(apiKey).build();
			ApplicationList applications = client.getApplications(Applications.where(Applications.name().eqIgnoreCase("Racom")));
			// ApplicationList applications = client.getApplications();
			for (Application application : applications) {
				System.out.println("NAME ==================> " + application.getName());
			}
			application = applications.iterator().next();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getKey(String userName, String password) {
		List<String> ret = new ArrayList<String>();
		AuthenticationRequest request = new UsernamePasswordRequest(userName, password);
		AuthenticationResult result = application.authenticateAccount(request);
		Account account = result.getAccount();
		ApiKeyList apiKeys = account.getApiKeys();
		for (ApiKey apiKey : apiKeys) {
			ret.add(apiKey.getId() + ":" + apiKey.getSecret());
		}
		return ret;
	}

	public boolean basicAuthen(HttpRequest request) {
		ApiAuthenticationResult result = application.authenticateApiRequest(request);
		// String email = result.getAccount().getEmail();
		// System.out.println(result.getApiKey());
		// System.out.println(result.getAccount());
		return result != null && result.getAccount() != null;

	}

	public String exchangeToken(HttpRequest request) {
		try {
			AccessTokenResult resultAuth = (AccessTokenResult) application.authenticateOauthRequest(request).execute();
			TokenResponse tokenResponse = resultAuth.getTokenResponse();
			System.out.println("Token response ===> " + tokenResponse.toJson());
			return tokenResponse.toJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean oAuthAuthen(HttpRequest request) {
		try {
			OauthAuthenticationResult result = (OauthAuthenticationResult) application.authenticateOauthRequest(request).execute();
			// System.out.println(result.getApiKey());
			// System.out.println(result.getAccount());
			return result != null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void validateAuthenMethod(HttpRequest request) {
		ApiAuthenticationResult result = application.authenticateApiRequest(request);
		result.accept(new AuthenticationResultVisitor() {
			@Override
			public void visit(AuthenticationResult result) {
				System.out.println("=======> 1");
			}

			@Override
			public void visit(AccessTokenResult result) {
				System.out.println("=======> 2");
			}

			@Override
			public void visit(ApiAuthenticationResult result) {
				System.out.println("=======> 3");
			}

			@Override
			public void visit(OauthAuthenticationResult result) {
				System.out.println("=======> 4");
			}

		});
	}

	public Account createAccount(User user) {
		Account account = generateAccountFromUser(user);
		Account createAccount = application.createAccount(account);
		ApiKey createApiKey = createAccount.createApiKey();
		return createAccount;
	}

	public Account generateAccountFromUser(User user) {
		Account account = client.instantiate(Account.class);
		account.setGivenName(user.getFirstName());
		account.setSurname(user.getLastName());
		account.setUsername(user.getUserName());
		account.setEmail(user.getEmail());
		account.setPassword(user.getPassword());
		return account;
	}

}
