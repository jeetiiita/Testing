package com.clicktable.support.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clicktable.model.Restaurant;
import com.clicktable.support.dao.intf.RestaurantPlanDao;
import com.clicktable.support.exception.ServiceValidationException;
import com.clicktable.support.model.RestaurantPlan;
import com.clicktable.support.service.intf.RestaurantPlanService;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.CustomValidations;
import com.clicktable.validate.ValidationError;

import play.Logger;

@Component
public class RestaurantPlanServiceImpl implements RestaurantPlanService {

    private static final Logger.ALogger log = Logger.of(RestaurantPlanServiceImpl.class);
    @Autowired
    private RestaurantPlanDao restaurantPlanDao;

    @Override
    public RestaurantPlan create(RestaurantPlan restaurantPlan) {
        log.info("RestaurantPlanServiceImpl.create()");
        RestaurantPlan restPlanEntity = restaurantPlanDao.create(restaurantPlan);
        return restPlanEntity;
    }

    @Override
    public RestaurantPlan getRestaurantPlanInvoice(Restaurant restaurant) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(SupportConstants.RESTAURANT_GUID, restaurant.getGuid());
        params.put(SupportConstants.STATUS, SupportConstants.ACTIVE);

        List<RestaurantPlan> restaurantPlanList = restaurantPlanDao.findByFields(RestaurantPlan.class, params);
        log.debug("RestaurantPlanServiceImpl.getRestaurantPlanInvoice() " + restaurant.getGuid());
        if (restaurantPlanList.size() == 1) {
            return restaurantPlanList.get(0);
        }
        return null;
    }

    @Override
    public List<RestaurantPlan> getRestaurantPlans(Map<String, Object> params) {
        List<ValidationError> errorList = new ArrayList<ValidationError>();
        List<RestaurantPlan> restaurantPlans = restaurantPlanDao.findByFields(RestaurantPlan.class, params);
        log.debug("RestaurantPlanServiceImpl.getRestaurantPlans() " + restaurantPlans);
        if (restaurantPlans.size() == 0) {
            log.warn("Exception in RestaurantPlan getRestaurantPlan Method " + ErrorCodes.REST_PLAN_DOESNOT_EXISTS);
            errorList = CustomValidations.populateErrorList(errorList, SupportConstants.REST_PLAN, UtilityMethods.getErrorMsg(ErrorCodes.REST_PLAN_DOESNOT_EXISTS), ErrorCodes.REST_PLAN_DOESNOT_EXISTS);
            throw new ServiceValidationException("Exception in RestaurantPlan getRestaurantPlan Method", errorList);
        }
        return restaurantPlans;
    }

    @Override
    public List<String> getRestaurantPlanByPlan(Map<String, Object> params) {
        List<ValidationError> errorList = new ArrayList<ValidationError>();

        if (params.get(SupportConstants.PLAN_ID) == null) {
            log.warn("Exception in RestaurantPlan getRestaurantPlanByPlan Method " + ErrorCodes.PLAN_REQUIRED);
            errorList = CustomValidations.populateErrorList(errorList, SupportConstants.PLAN_ID,
                    UtilityMethods.getErrorMsg(ErrorCodes.PLAN_REQUIRED), ErrorCodes.PLAN_REQUIRED);
            throw new ServiceValidationException("Exception in RestaurantPlan getRestaurantPlanByPlan Method",
                    errorList);

        }
        List<String> restaurantList = restaurantPlanDao.findRestaurantPlan(params);

        return restaurantList;
    }

    @Override
    public RestaurantPlan disableRestaurantPlan(Integer id) {
        List<ValidationError> errorList = new ArrayList<ValidationError>();

        RestaurantPlan restaurantPlan = restaurantPlanDao.find(id);
        if (restaurantPlan == null) {
            log.warn(" Exception in RestaurantPlan disable method " + ErrorCodes.REST_PLAN_DOESNOT_EXISTS);
            errorList = CustomValidations.populateErrorList(errorList, SupportConstants.REST_PLAN, UtilityMethods.getErrorMsg(ErrorCodes.REST_PLAN_DOESNOT_EXISTS), ErrorCodes.REST_PLAN_DOESNOT_EXISTS);
            throw new ServiceValidationException("Exception in RestaurantPlan disable method", errorList);
        } else if (SupportConstants.INACTIVE_STATUS.equals(restaurantPlan.getStatus())) {
            log.warn(" Exception in RestaurantPlan disable method " + ErrorCodes.REST_PLAN_INACTIVE);
            errorList = CustomValidations.populateErrorList(errorList, SupportConstants.REST_PLAN, UtilityMethods.getErrorMsg(ErrorCodes.REST_PLAN_INACTIVE), ErrorCodes.REST_PLAN_INACTIVE);
            throw new ServiceValidationException("Exception in RestaurantPlan disable method", errorList);
        }
        restaurantPlan.setStatus(SupportConstants.INACTIVE_STATUS);
        restaurantPlan = restaurantPlanDao.update(restaurantPlan);
        return restaurantPlan;
    }
}
