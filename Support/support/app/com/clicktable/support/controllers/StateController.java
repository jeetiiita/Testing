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
import com.clicktable.support.model.State;
import com.clicktable.support.service.impl.StateServiceImpl;
import com.clicktable.support.util.SupportResponseCodes;
import com.clicktable.support.validate.StateValidator;
import com.clicktable.util.ResponseCodes;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.ValidationError;
import com.fasterxml.jackson.databind.JsonNode;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

@org.springframework.stereotype.Controller
public class StateController extends Controller {

    private static final Logger.ALogger log = Logger.of(State.class);

    @Autowired
    StateValidator stateValidator;

    @Autowired
    StateServiceImpl stateService;

    public Result getState() {

        Map<String, Object> stringParamMap = UtilityMethods.convertQueryStringToMap(request().queryString());
        List<State> stateList = stateService.getState(stringParamMap);
        BaseResponse response = new GetResponse<State>(ResponseCodes.STATE_RECORD_FETCH_SUCCESFULLY, stateList);
        JsonNode result = Json.toJson(response);
        return ok(result);
    }

    public Result addState() {
        BaseResponse response = null;
        State state = Json.fromJson(request().body().asJson(), State.class);
        List<ValidationError> errorList = stateValidator.validateStateOnAdd(state);

        if (errorList.isEmpty()) {
            State stateEntity = stateService.addState(state);
            response = new PostResponse<State>(SupportResponseCodes.STATE_ADDED_SUCCESFULLY,
                    stateEntity.getId().toString());
        } else
            response = new ErrorResponse(SupportResponseCodes.STATE_ADDITION_FAILURE, errorList);

        JsonNode result = Json.toJson(response);
        return ok(result);
    }

    public Result disableState(Integer id) {
        Integer stateId = id;
        BaseResponse response;
        try {
            stateService.disable(stateId);

            response = new UpdateResponse<State>(
                    SupportResponseCodes.STATE_UPDATED_SUCCESFULLY, stateId);

        } catch (ServiceValidationException e) {
            log.debug(e.getMessage());
            response = new ErrorResponse(SupportResponseCodes.STATE_UPDATION_FAILURE,
                    e.getErrorList());
        }
        JsonNode result = Json.toJson(response);
        return ok(result);
    }


}
