/**
 *
 */
package com.clicktable.support.service.intf;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.clicktable.support.model.Plan;
import com.clicktable.support.model.PlanItem;

/**
 * @author j.yadav
 */
@Service
public interface PlanService {


    Plan create(Plan plan);

    List<Plan> getPlans(Map<String, Object> params);

    Set<PlanItem> getPlanItems(Plan plan);

    Plan disablePlan(Integer id);

}
