package com.clicktable.support.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import play.mvc.Controller;
import play.mvc.Result;

import com.clicktable.support.service.intf.InvoicePdfService;

@org.springframework.stereotype.Controller
public class Application extends Controller {

    @Autowired
    InvoicePdfService invoicePdfService;

    public static Result preflight(String all) {
        //response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Access-Control-Allow-Origin", (null == request().getHeader("Origin")) ? "*" : String.valueOf(request().getHeader("Origin")));
        //response().setHeader("Allow", "*");
        //response().setHeader("Access-Control-Allow-Credentaials", "*");
        response().setHeader("Access-Control-Allow-Credentials", "true");
        response().setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS, PATCH");
        response().setHeader("Access-Control-Allow-Headers", "Origin,'X-Requested-With, Content-Type, Accept, Referer, User-Agent, access_token, mode, Authorization,withCredentials");
        return ok();
    }

    public Result index() {
        return ok("Your new application is ready.");
    }

}
