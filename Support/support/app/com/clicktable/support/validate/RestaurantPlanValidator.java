package com.clicktable.support.validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clicktable.support.dao.intf.PlanDao;
import com.clicktable.support.dao.intf.RestaurantPlanDao;
import com.clicktable.support.model.Plan;
import com.clicktable.support.model.RestaurantPlan;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.EntityValidator;
import com.clicktable.validate.ValidationError;

@Service
public class RestaurantPlanValidator extends EntityValidator<RestaurantPlan> {

    @Autowired
    private PlanDao planDao;

    @Autowired
    private RestaurantPlanDao restaurantPlanDao;

    public List<ValidationError> validateRestaurantPlanOnAdd(RestaurantPlan restaurantPlan) {
        // TODO Auto-generated method stub

        List<ValidationError> errorList = validateOnAdd(restaurantPlan);
        if (errorList.isEmpty()) {
            Plan plan = planDao.find(restaurantPlan.getPlan().getId());
            if (plan == null) {
                errorList.add(new ValidationError(SupportConstants.PLAN, UtilityMethods.getErrorMsg(ErrorCodes.PLAN_ABSENT), ErrorCodes.PLAN_ABSENT));
            } else if (SupportConstants.INACTIVE_STATUS.equals(plan.getStatus())) {
                errorList.add(new ValidationError(SupportConstants.STATUS, UtilityMethods.getErrorMsg(ErrorCodes.PLAN_STATUS_INACTIVE), ErrorCodes.PLAN_STATUS_INACTIVE));
            }
            if (errorList.isEmpty()) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put(SupportConstants.RESTAURANT_GUID, restaurantPlan.getRestaurantGuid());
                params.put(SupportConstants.STATUS, restaurantPlan.getStatus());
                List<RestaurantPlan> restaurantPlanList = restaurantPlanDao.findByFields(RestaurantPlan.class, params);
                if (!restaurantPlanList.isEmpty()) {
                    errorList.add(new ValidationError(SupportConstants.REST_PLAN, UtilityMethods.getErrorMsg(ErrorCodes.REST_PLAN_EXISTS), ErrorCodes.REST_PLAN_EXISTS));
                }
            }
        }
        return errorList;
    }

}
