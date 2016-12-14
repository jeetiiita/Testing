package com.clicktable.support.validate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clicktable.support.dao.intf.ItemDao;
import com.clicktable.support.dao.intf.PlanDao;
import com.clicktable.support.model.Item;
import com.clicktable.support.model.Plan;
import com.clicktable.support.model.PlanItem;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.EntityValidator;
import com.clicktable.validate.ValidationError;

@Service
public class PlanValidator extends EntityValidator<Plan> {

    @Autowired
    private PlanDao planDao;

    @Autowired
    private ItemDao itemDao;

    public List<ValidationError> validatePlanOnAdd(Plan plan) {
        List<ValidationError> errorList = validateOnAdd(plan);
        if (errorList.isEmpty()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(SupportConstants.STATUS, SupportConstants.ACTIVE_STATUS);
            params.put(SupportConstants.NAME, plan.getName());
            List<Plan> planList = planDao.findByFields(Plan.class, params);
            if (!planList.isEmpty()) {
                errorList.add(new ValidationError(SupportConstants.NAME, UtilityMethods.getErrorMsg(ErrorCodes.PLAN_WITH_NAME_EXISTS), ErrorCodes.PLAN_WITH_NAME_EXISTS));
            }
            Set<PlanItem> planItems = plan.getPlanItems();
            Set<Short> set = new HashSet<Short>();
            for (PlanItem planItem : planItems) {
                Short itemId = planItem.getItem().getId();
                if (set.contains(itemId)) {
                    errorList.add(new ValidationError(SupportConstants.ITEM, UtilityMethods.getErrorMsg(ErrorCodes.ITEM_DUPLICATE), ErrorCodes.ITEM_DUPLICATE));
                } else {
                    set.add(itemId);
                    Item item = itemDao.find(itemId);
                    if (item == null) {
                        errorList.add(new ValidationError(SupportConstants.ITEM, UtilityMethods.getErrorMsg(ErrorCodes.ITEM_ABSENT), ErrorCodes.ITEM_ABSENT));
                    } else if (SupportConstants.INACTIVE_STATUS.equals(item.getStatus())) {
                        errorList.add(new ValidationError(SupportConstants.STATUS, UtilityMethods.getErrorMsg(ErrorCodes.ITEM_STATUS_INACTIVE), ErrorCodes.ITEM_STATUS_INACTIVE));
                    }
                }
            }
        }
        return errorList;
    }
}
