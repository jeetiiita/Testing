/**
 *
 */
package com.clicktable.support.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.clicktable.response.BaseResponse;
import com.clicktable.response.ErrorResponse;
import com.clicktable.response.GetResponse;
import com.clicktable.response.PostResponse;
import com.clicktable.response.UpdateResponse;
import com.clicktable.support.exception.ServiceValidationException;
import com.clicktable.support.model.AggregateTax;
import com.clicktable.support.model.SingleTax;
import com.clicktable.support.model.TaxLayer;
import com.clicktable.support.service.intf.AggregateTaxService;
import com.clicktable.support.util.SupportResponseCodes;
import com.clicktable.support.validate.AggregateTaxValidator;
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
public class AggregateTaxController extends Controller {

    private static final Logger.ALogger log = Logger.of(AggregateTaxController.class);

    @Autowired
    private AggregateTaxService aggregateTaxService;

    @Autowired
    private AggregateTaxValidator aggregateTaxValidator;

    public Result addAggregateTax() {

        BaseResponse response = null;
        JsonNode json = request().body().asJson();

        AggregateTax aggregateTax = Json.fromJson(json, AggregateTax.class);

        List<ValidationError> errorList = aggregateTaxValidator.validateTaxOnAdd(aggregateTax);

        Set<TaxLayer> taxLayers = aggregateTax.getTaxLayers();

        for (TaxLayer taxLayer : taxLayers) {
            taxLayer.setCreatedBy(aggregateTax.getCreatedBy());
            taxLayer.setUpdatedBy(aggregateTax.getUpdatedBy());
        }

        if (errorList.isEmpty()) {
            aggregateTax.setEndDate(null);

            if (aggregateTax.getCentralTax() == true) {
                aggregateTax.setState(null);
            }

            AggregateTax taxEntity = aggregateTaxService.create(aggregateTax);

            response = new PostResponse<SingleTax>(SupportResponseCodes.AGGREGATE_TAX_ADDED_SUCCESSFULLY,
                    taxEntity.getId().toString());
        } else
            response = new ErrorResponse(SupportResponseCodes.AGGREGATE_TAX_ADDITION_FAILURE, errorList);

        JsonNode result = Json.toJson(response);
        return ok(result);
    }

    public Result getAggregateTaxes() {

        Map<String, Object> params = UtilityMethods.convertQueryStringToMap(request().queryString());
        Map<String, Object> validateParams = aggregateTaxValidator.validateFinderParams(params, AggregateTax.class);

        List<AggregateTax> taxEntityList = aggregateTaxService.getAggregateTax(validateParams);
        BaseResponse response = new GetResponse<AggregateTax>(SupportResponseCodes.AGGREGATE_TAX_FETCHED_SUCCESSFULLY,
                taxEntityList);

        JsonNode result = Json.toJson(response);
        return ok(Json.toJson(result));
    }

    public Result disableAggregateTax(Integer id) {
        Integer taxId = id;
        BaseResponse response;
        try {
            aggregateTaxService.disable(taxId);
            response = new UpdateResponse<AggregateTax>(SupportResponseCodes.AGGREGATE_TAX_UPDATED_SUCCESSFULLY, taxId);

        } catch (ServiceValidationException e) {
            log.debug(e.getMessage());
            response = new ErrorResponse(SupportResponseCodes.AGGREGATE_TAX_UPDATION_FAILURE, e.getErrorList());
        }
        JsonNode result = Json.toJson(response);
        return ok(result);
    }

}
