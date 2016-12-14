package com.clicktable.support.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.clicktable.support.model.Delivery;
import com.clicktable.response.BaseResponse;
import com.clicktable.response.ErrorResponse;
import com.clicktable.response.GetResponse;
import com.clicktable.support.model.GuestConversation;
import com.clicktable.support.model.PromotionSummary;
import com.clicktable.support.service.intf.ConversationService;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.SupportResponseCodes;
import com.clicktable.util.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 */
@org.springframework.stereotype.Controller
public class ConversationController extends Controller {
    private static final Logger.ALogger log = Logger.of(ConversationController.class);

    /**
     * Field conversationService.
     */
    @Autowired
    private ConversationService conversationService;

    /**
     * Method insertGuestConversation.
     *
     * @return Result
     */
    public Result insertGuestConversation() {
        BaseResponse response = null;
        JsonNode json = request().body().asJson();
        GuestConversation conversation = Json.fromJson(json, GuestConversation.class);

        response = conversationService.insertToConversation(conversation);
        JsonNode result = Json.toJson(response);
        return ok(result);
    }

    /**
     * Method insertMultipleGuestConversation.
     *
     * @return Result
     */
    public Result insertMultipleGuestConversation() {
        BaseResponse response = null;
        JsonNode json = request().body().asJson();
        JsonNode jsonConversation = json.get(SupportConstants.CONVERSATION_NODE);
        JsonNode jsonGuestInfo = json.get(SupportConstants.GUEST_INFO_NODE);
        if (jsonConversation == null && jsonGuestInfo == null) {
            return ok("Information node missing!");
        }
        GuestConversation conversation = Json.fromJson(jsonConversation, GuestConversation.class);

        Map<String, String> infoMap = new HashMap<String, String>();
        for (JsonNode obj : jsonGuestInfo) {
            infoMap.put(obj.get("guestMobileNum").asText(), obj.get("guestGuid").asText());
        }
        response = conversationService.insertToMultipleConversation(conversation, infoMap);

        JsonNode result = Json.toJson(response);
        return ok(result);
    }

    /**
     * Method getGuestConversation.
     *
     * @return Result
     */
    public Result getGuestConversation() {
        Map<String, Object> params = UtilityMethods.convertQueryStringToMap(request().queryString());
        BaseResponse response = conversationService.getGuestConversation(params);
        JsonNode result = Json.toJson(response);
        log.debug(Json.stringify(result));
        return ok(result);
    }

    /**
     * Method updateConversation.
     *
     * @return Result
     */
    public Result updateConversation() {
        JsonNode json = request().body().asJson();
        GuestConversation conversation = Json.fromJson(json, GuestConversation.class);
        BaseResponse response = conversationService.updateGuestConversation(conversation);
        return ok(Json.toJson(response));
    }

    /**
     * Method delivery.
     *
     */
    public Result delivery() {

        Map<String, Object> params = UtilityMethods.convertQueryStringToMap(request().queryString());
        Delivery delivery = Json.fromJson(Json.toJson(params), Delivery.class);
        conversationService.addDeliveryToQueue(delivery);
        return ok("ok");
    }

    public Result getResaturantEventPromotion() {
        Map<String, Object> params = UtilityMethods.convertQueryStringToMap(request().queryString());
        List<PromotionSummary> resultList = conversationService.getEventPromotionOfRestaurant(params);
        BaseResponse response;
        if (resultList != null) {
            response = new GetResponse<PromotionSummary>(
                    SupportResponseCodes.RESTAURANT_EVENT_PROMOTION_MESSAGES_FETCHED_SUCCESSFULLY, resultList);
        } else {
            response = new ErrorResponse(SupportResponseCodes.RESTAURANT_EVENT_PROMOTION_FECTCHED_FAILED, null);
        }

        JsonNode result = Json.toJson(response);
        return ok(result);
    }

}
