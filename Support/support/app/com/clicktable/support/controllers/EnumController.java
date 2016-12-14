package com.clicktable.support.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.clicktable.response.BaseResponse;
import com.clicktable.response.GetResponse;
import com.clicktable.util.ResponseCodes;
import com.clicktable.util.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;

@org.springframework.stereotype.Controller
public class EnumController extends Controller {
    private static final Logger.ALogger log = Logger.of(EnumController.class);

    public static Map<String, String> getAllSupportEnums() {

        Config config = ConfigFactory.parseResources("support.enum.properties");
        Map<String, String> enumMap = new HashMap<>();

        Set<Entry<String, ConfigValue>> entrySet = config.entrySet();

        for (Entry<String, ConfigValue> entry : entrySet) {

            enumMap.put(entry.getKey(), entry.getValue().render().replaceAll("\"", ""));
        }

        return enumMap;
    }

    public Result getAllEnums() {
        Map<String, Object> stringParamMap = UtilityMethods.convertQueryStringToMap(request().queryString());
        log.debug(stringParamMap.toString());
        Map<String, String> enumMap = getAllSupportEnums();
        log.debug("enum map is " + enumMap);
        List<Map<String, String>> responseList = new ArrayList<Map<String, String>>();
        responseList.add(enumMap);
        BaseResponse response = new GetResponse<>(ResponseCodes.ENUM_DATA_FETCH_SUCCESFULLY, responseList);

        JsonNode result = Json.toJson(response);
        log.debug(Json.stringify(result));
        return ok(result);
    }

}
