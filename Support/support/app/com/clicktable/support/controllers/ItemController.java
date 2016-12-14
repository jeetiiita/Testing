package com.clicktable.support.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.clicktable.response.BaseResponse;
import com.clicktable.response.ErrorResponse;
import com.clicktable.response.GetResponse;
import com.clicktable.response.PostResponse;
import com.clicktable.response.UpdateResponse;
import com.clicktable.support.model.Item;
import com.clicktable.support.service.intf.ItemService;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.SupportResponseCodes;
import com.clicktable.support.validate.ItemValidator;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.ValidationError;
import com.fasterxml.jackson.databind.JsonNode;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

@org.springframework.stereotype.Controller
public class ItemController extends Controller {

    private static final Logger.ALogger log = Logger.of(ItemController.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemValidator itemValidator;

    public Result createItem() {

        BaseResponse response;
        Item item = Json.fromJson(request().body().asJson(), Item.class);
        item.setStatus(SupportConstants.ACTIVE_STATUS);
        List<ValidationError> listOfError = itemValidator.validateItemOnAdd(item);
        if (listOfError.isEmpty()) {
            item = itemService.create(item);
            response = new PostResponse<Item>(SupportResponseCodes.ITEM_ADDED_SUCCESSFULLY, item.getId().toString());

        } else {
            response = new ErrorResponse(SupportResponseCodes.ITEM_ADDITION_FAILURE, listOfError);
        }
        JsonNode result = Json.toJson(response);
        return ok(result);
    }

    public Result getItems() {
        BaseResponse response;
        Map<String, Object> params = UtilityMethods.convertQueryStringToMap(request().queryString());
        Map<String, Object> validateParams = itemValidator.validateFinderParams(params, Item.class);
        log.info(params.toString());
        List<Item> itemList = itemService.getItems(validateParams);
        response = new GetResponse<Item>(SupportResponseCodes.ITEM_FETCHED_SUCCESSFULLY, itemList);
        JsonNode result = Json.toJson(response);
        return ok(result);
    }

    public Result updateItem() {
        BaseResponse response;
        Item item = Json.fromJson(request().body().asJson(), Item.class);
        List<ValidationError> listOfError = itemValidator.validateItemOnUpdate(item);

        if (listOfError.isEmpty()) {
            item = itemService.updateItem(item);
            response = new UpdateResponse<Item>(SupportResponseCodes.ITEM_UPDATED_SUCCESSFULLY,
                    item.getId().toString());

        } else {
            response = new ErrorResponse(SupportResponseCodes.ITEM_UPDATION_FAILURE, listOfError);
        }
        JsonNode result = Json.toJson(response);
        return ok(result);

    }
}
