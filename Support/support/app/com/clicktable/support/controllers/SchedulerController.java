package com.clicktable.support.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.clicktable.response.BaseResponse;
import com.clicktable.support.service.intf.SchedulerService;
import com.clicktable.util.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;


@org.springframework.stereotype.Controller
public class SchedulerController extends Controller {

    @Autowired
    SchedulerService schedulerService;

    public Result runJob() {

        Map<String, Object> params = UtilityMethods.convertQueryStringToMap(request().queryString());
        BaseResponse response = schedulerService.runInvoiceGenrationScheduler(params);
        JsonNode result = Json.toJson(response);
        return ok(result);
    }

}
