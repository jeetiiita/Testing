package com.clicktable.support.dao.intf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clicktable.dao.intf.GenericDao;
import com.clicktable.support.model.RestaurantPlan;

@Service
public interface RestaurantPlanDao extends GenericDao<RestaurantPlan> {

    List<String> findRestaurantPlan(Map<String, Object> params);
}
