package com.clicktable.support.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.clicktable.response.BaseResponse;
import com.clicktable.response.ErrorResponse;
import com.clicktable.response.GetResponse;
import com.clicktable.response.PostResponse;
import com.clicktable.response.UpdateResponse;
import com.clicktable.support.exception.ServiceValidationException;
import com.clicktable.support.model.RestaurantPlan;
import com.clicktable.support.service.intf.RestaurantPlanService;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.SupportResponseCodes;
import com.clicktable.support.validate.RestaurantPlanValidator;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.ValidationError;
import com.fasterxml.jackson.databind.JsonNode;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

@org.springframework.stereotype.Controller
public class RestaurantPlanController extends Controller {

    private static final Logger.ALogger log = Logger.of(RestaurantPlanController.class);

    @Autowired
    private RestaurantPlanService restaurantPlanService;

    @Autowired
    private RestaurantPlanValidator restaurantPlanValidator;

    public Result createRestaurantPlan() {

        BaseResponse response = null;

        RestaurantPlan restaurantPlan = Json.fromJson(request().body().asJson(), RestaurantPlan.class);
        restaurantPlan.setStatus(SupportConstants.ACTIVE_STATUS);
        List<ValidationError> errorList = restaurantPlanValidator.validateRestaurantPlanOnAdd(restaurantPlan);
        if (errorList.isEmpty()) {
            restaurantPlan = restaurantPlanService.create(restaurantPlan);
            response = new PostResponse<RestaurantPlan>(SupportResponseCodes.REST_PLAN_ADDED_SUCCESSFULLY, restaurantPlan.getId().toString());
        } else {
            response = new ErrorResponse(SupportResponseCodes.REST_PLAN_ADDITION_FAILURE, errorList);
        }
        JsonNode result = Json.toJson(response);
        return ok(result);
    }

    public Result getRestaurantPlan() {
        BaseResponse response = null;
        Map<String, Object> params = UtilityMethods.convertQueryStringToMap(request().queryString());
        try {
            List<RestaurantPlan> restaurantPlan = restaurantPlanService.getRestaurantPlans(params);
            response = new GetResponse<RestaurantPlan>(SupportResponseCodes.REST_PLAN_FETCHED_SUCCESSFULLY, restaurantPlan);
        } catch (ServiceValidationException e) {
            log.warn("Exception in RestaurantPlan getRestaurantPlan Method " + ErrorCodes.REST_PLAN_DOESNOT_EXISTS, e);
            response = new ErrorResponse(SupportResponseCodes.REST_PLAN_FETCH_FAILURE, e.getErrorList());
        }
        JsonNode result = Json.toJson(response);
        return ok(result);
    }

    public Result disableRestaurantPlan(Integer restaurantPlanId) {
        BaseResponse response = null;
        try {
            RestaurantPlan restaurantPlan = restaurantPlanService.disableRestaurantPlan(restaurantPlanId);
            response = new UpdateResponse<RestaurantPlan>(SupportResponseCodes.REST_PLAN_UPDATED_SUCCESSFULLY, restaurantPlan.getId());
        } catch (ServiceValidationException e) {
            log.warn(" Exception in RestaurantPlan disable.  RestaurantPlan does not exits or is inactive", e);
            response = new ErrorResponse(SupportResponseCodes.REST_PLAN_UPDATION_FAILURE, e.getErrorList());
        }
        JsonNode result = Json.toJson(response);
        return ok(result);
    }

    public Result getRestaurantPlanByPlan() {
        BaseResponse response = null;
        Map<String, Object> params = UtilityMethods.convertQueryStringToMap(request().queryString());
        params.put(SupportConstants.STATUS, SupportConstants.ACTIVE_STATUS);
        try {
            List<String> restaurantPlans = restaurantPlanService.getRestaurantPlanByPlan(params);
            response = new GetResponse<String>(SupportResponseCodes.REST_PLAN_FETCHED_SUCCESSFULLY,
                    restaurantPlans);
        } catch (ServiceValidationException e) {
            e.printStackTrace();
            response = new ErrorResponse(SupportResponseCodes.REST_PLAN_FETCH_FAILURE, e.getErrorList());
        }
        JsonNode result = Json.toJson(response);
        return ok(result);
    }
}
