/**
 *
 */
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
import com.clicktable.support.model.SingleTax;
import com.clicktable.support.service.intf.SingleTaxService;
import com.clicktable.support.util.SupportResponseCodes;
import com.clicktable.support.validate.SingleTaxValidator;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.ValidationError;
import com.fasterxml.jackson.databind.JsonNode;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author j.yadav
 */
@org.springframework.stereotype.Controller
public class SingleTaxController extends Controller {

    private static final Logger.ALogger log = Logger.of(SingleTaxController.class);

    @Autowired
    private SingleTaxService singleTaxService;

    @Autowired
    private SingleTaxValidator singleTaxValidator;

    public Result addSingleTax() {

        BaseResponse response = null;

        JsonNode json = request().body().asJson();

        SingleTax singleTax = Json.fromJson(json, SingleTax.class);

        List<ValidationError> errorList = singleTaxValidator.validateTaxOnAdd(singleTax);

        if (errorList.isEmpty()) {

            if (singleTax.getCentralTax() == true) {
                singleTax.setState(null);
            }
            SingleTax taxEntity = singleTaxService.create(singleTax);

            response = new PostResponse<SingleTax>(SupportResponseCodes.SINGLE_TAX_ADDED_SUCCESSFULLY,
                    taxEntity.getId().toString());
        } else
            response = new ErrorResponse(SupportResponseCodes.SINGLE_TAX_ADDITION_FAILURE, errorList);

        JsonNode result = Json.toJson(response);
        return ok(result);
    }

    public Result getSingleTaxes() {

        Map<String, Object> params = UtilityMethods.convertQueryStringToMap(request().queryString());
        Map<String, Object> validateParams = singleTaxValidator.validateFinderParams(params, SingleTax.class);

        List<SingleTax> taxEntityList = singleTaxService.getSingleTaxes(validateParams);

        BaseResponse response = new GetResponse<SingleTax>(SupportResponseCodes.SINGLE_TAX_FETCHED_SUCCESSFULLY,
                taxEntityList);

        JsonNode result = Json.toJson(response);
        return ok(Json.toJson(result));
    }

    public Result disableSingleTax(Integer id) {
        Integer taxId = id;
        BaseResponse response;
        try {
            singleTaxService.disable(taxId);

            response = new UpdateResponse<SingleTax>(SupportResponseCodes.SINGLE_TAX_UPDATED_SUCCESSFULLY, taxId);

        } catch (ServiceValidationException e) {
            log.warn("Exception:" + e.getMessage());
            response = new ErrorResponse(SupportResponseCodes.SINGLE_TAX_UPDATION_FAILURE, e.getErrorList());
        }
        JsonNode result = Json.toJson(response);
        return ok(result);
    }
}
