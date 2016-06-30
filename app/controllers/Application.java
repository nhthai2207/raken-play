package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import play.api.templates.Html;
import play.mvc.Controller;
import play.mvc.Result;
import racom.be.service.template.TemplateService;

@org.springframework.stereotype.Controller
public class Application extends Controller {
	public final static String STATUS = "status";
	public final static String ERROR_CODE = "error_code";
	public final static String OK = "ok";
	public final static String NG = "ng";
	public final static String MESSAGE = "message";
	public final static int UN_AUTHEN = 200;
	
	@Autowired
	@Qualifier("localTemplateServiceImpl")
	TemplateService templateService;
    public  Result index() {
        return ok(Html.apply(templateService.compileTemplate("index.html")));
    }
    public  Result fbads() {
        return ok(Html.apply(templateService.compileTemplate("fbads.html")));
    }

}
