/**
 *
 */
package com.clicktable.support.service.intf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clicktable.model.Restaurant;
import com.clicktable.support.model.RestaurantPlan;

/**
 * @author j.yadav
 */
@Service
public interface RestaurantPlanService {

    RestaurantPlan create(RestaurantPlan restPlan);

    RestaurantPlan getRestaurantPlanInvoice(Restaurant restaurant);

    List<RestaurantPlan> getRestaurantPlans(Map<String, Object> params);

    RestaurantPlan disableRestaurantPlan(Integer id);

    List<String> getRestaurantPlanByPlan(Map<String, Object> params);

}
