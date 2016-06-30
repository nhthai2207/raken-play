package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import play.libs.F.Function0;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.SimpleResult;
import racom.be.service.AuthenService;
import racom.be.service.PlayStormPathRequest;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class Authentication extends Action<OAuth2> {
	@Autowired
	@Qualifier("authenService")
	AuthenService authenService;

	public Promise<SimpleResult> call(Http.Context ctx) throws Throwable {
		boolean oAuthAuthen = authenService.oAuthAuthen(new PlayStormPathRequest(ctx.request()));

		if (oAuthAuthen) {
			return delegate.call(ctx);
		} else {
			Promise<SimpleResult> ret = Promise.promise(new Function0<SimpleResult>() {
				public SimpleResult apply() {
					ObjectNode result = Json.newObject();
					result.put(Application.STATUS, Application.NG);
					result.put(Application.ERROR_CODE, Application.UN_AUTHEN);
					result.put(Application.MESSAGE, "Your request is not authen. Please call API /racom/oauth/token to get token, then put token to header for each request");
					return ok(result);
				}
			});
			return ret;
		}

	}
}