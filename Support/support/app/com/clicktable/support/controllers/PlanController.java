package com.clicktable.support.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.clicktable.response.BaseResponse;
import com.clicktable.response.ErrorResponse;
import com.clicktable.response.GetResponse;
import com.clicktable.response.PostResponse;
import com.clicktable.response.UpdateResponse;
import com.clicktable.support.exception.ServiceValidationException;
import com.clicktable.support.model.Plan;
import com.clicktable.support.response.SupportServerResponse;
import com.clicktable.support.service.intf.PlanService;
import com.clicktable.support.service.intf.RestaurantPlanService;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.SupportResponseCodes;
import com.clicktable.support.validate.PlanValidator;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.CustomValidations;
import com.clicktable.validate.ValidationError;
import com.fasterxml.jackson.databind.JsonNode;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

@org.springframework.stereotype.Controller
public class PlanController extends Controller {

    private static final Logger.ALogger log = Logger.of(PlanController.class);

    @Autowired
    private PlanService planService;

    @Autowired
    private PlanValidator planValidator;

    @Autowired
    private RestaurantPlanService restaurantPlanService;

    public Result createPlan() {
        BaseResponse response;

        Plan plan = Json.fromJson(request().body().asJson(), Plan.class);
        plan.setStatus(SupportConstants.ACTIVE_STATUS);
        List<ValidationError> listOfError = planValidator.validatePlanOnAdd(plan);
        if (listOfError.isEmpty()) {
            plan = planService.create(plan);
            response = new PostResponse<Plan>(SupportResponseCodes.PLAN_ADDED_SUCCESSFULLY, plan.getId().toString());
        } else {
            response = new ErrorResponse(SupportResponseCodes.PLAN_ADDITION_FAILURE, listOfError);
        }
        JsonNode result = Json.toJson(response);
        return ok(result);
    }

    public Result getPlans() {
        BaseResponse response;
        Map<String, Object> params = UtilityMethods.convertQueryStringToMap(request().queryString());
        Map<String, Object> validParams = planValidator.validateFinderParams(params, Plan.class);
        List<Plan> PlanList = planService.getPlans(validParams);
        response = new GetResponse<Plan>(SupportResponseCodes.PLAN_FETCHED_SUCCESSFULLY, PlanList);
        JsonNode result = Json.toJson(response);
        return ok(result);
    }

    public Result disablePlan(Integer planId) {
        BaseResponse response = null;
        List<ValidationError> errorList = new ArrayList<ValidationError>();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(SupportConstants.PLAN_ID, planId);
            params.put(SupportConstants.STATUS, SupportConstants.ACTIVE_STATUS);

            List<String> restaurantList = restaurantPlanService.getRestaurantPlanByPlan(params);
            if (!restaurantList.isEmpty()) {
                log.warn("RestaurantPlan disable method " + ErrorCodes.REST_PLAN_EXISTS);
                errorList = CustomValidations.populateErrorList(errorList, SupportConstants.REST_PLAN,
                        UtilityMethods.getErrorMsg(ErrorCodes.REST_PLAN_EXISTS), ErrorCodes.REST_PLAN_EXISTS);
                response = new SupportServerResponse(SupportResponseCodes.PLAN_UPDATION_FAILURE, errorList,
                        restaurantList);
            } else {
                Plan plan = planService.disablePlan(planId);
                response = new UpdateResponse<Plan>(SupportResponseCodes.PLAN_UPDATED_SUCCESSFULLY,
                        plan.getId().toString());
            }
        } catch (ServiceValidationException e) {
            log.info(e.getMessage());
            response = new ErrorResponse(SupportResponseCodes.PLAN_UPDATION_FAILURE, e.getErrorList());
        }
        JsonNode result = Json.toJson(response);
        return ok(result);
    }
}
