package com.clicktable.support.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clicktable.support.dao.intf.PlanDao;
import com.clicktable.support.exception.ServiceValidationException;
import com.clicktable.support.model.Plan;
import com.clicktable.support.model.PlanItem;
import com.clicktable.support.service.intf.PlanService;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.CustomValidations;
import com.clicktable.validate.ValidationError;

import play.Logger;

@Component
public class PlanServiceImpl implements PlanService {

    private static final Logger.ALogger log = Logger.of(PlanServiceImpl.class);

    @Autowired
    private PlanDao planDao;

    @Override
    public Plan create(Plan plan) {
        log.info("PlanServiceImpl.create() start");
        Plan planEntity = planDao.create(plan);
        log.info("PlanServiceImpl.create() end");
        return planEntity;
    }


    @Override
    public List<Plan> getPlans(Map<String, Object> params) {
        List<Plan> planList = planDao.findByFields(Plan.class, params);
        log.debug("PlanServiceImpl.getPlans() " + planList);
        return planList;
    }


    @Override
    public Set<PlanItem> getPlanItems(Plan plan) {
        Plan planEntity = planDao.find(plan.getId());
        Set<PlanItem> planItems = planEntity.getPlanItems();
        log.debug("PlanServiceImpl.getPlanItems() " + planItems);
        return planItems;
    }

    @Override
    public Plan disablePlan(Integer id) {
        List<ValidationError> errorList = new ArrayList<ValidationError>();

        Plan plan = planDao.find(id);
        if (plan == null) {
            log.warn("Exception in RestaurantPlan disable method " + ErrorCodes.PLAN_DOESNOT_EXISTS);
            errorList = CustomValidations.populateErrorList(errorList, SupportConstants.PLAN, UtilityMethods.getErrorMsg(ErrorCodes.PLAN_DOESNOT_EXISTS), ErrorCodes.PLAN_DOESNOT_EXISTS);
            throw new ServiceValidationException("Exception in Plan disable method", errorList);
        } else if (SupportConstants.INACTIVE_STATUS.equals(plan.getStatus())) {
            log.warn("Exception in RestaurantPlan disable method " + ErrorCodes.PLAN_STATUS_INACTIVE);
            errorList = CustomValidations.populateErrorList(errorList, SupportConstants.PLAN, UtilityMethods.getErrorMsg(ErrorCodes.PLAN_STATUS_INACTIVE), ErrorCodes.PLAN_STATUS_INACTIVE);
            throw new ServiceValidationException("Exception in Plan disable method", errorList);
        }
        plan.setStatus(SupportConstants.INACTIVE_STATUS);
        plan = planDao.update(plan);
        return plan;
    }

}
