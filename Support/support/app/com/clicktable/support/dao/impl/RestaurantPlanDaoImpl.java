package com.clicktable.support.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.clicktable.support.dao.intf.RestaurantPlanDao;
import com.clicktable.support.model.RestaurantPlan;
import com.clicktable.support.util.SupportConstants;

@Component
public class RestaurantPlanDaoImpl extends JPADao<RestaurantPlan> implements RestaurantPlanDao {

    @Override
    public List<String> findRestaurantPlan(Map<String, Object> params) {
        String queryStr = "Select rp.restaurantGuid FROM RestaurantPlan as rp WHERE rp.plan.id=:planId AND status=:status";

        Query query = em.createQuery(queryStr)
                .setParameter(SupportConstants.PLAN_ID, Integer.parseInt(params.get(SupportConstants.PLAN_ID).toString()))
                .setParameter(SupportConstants.STATUS, SupportConstants.ACTIVE);

        List<String> restaurantList = query.getResultList();
        return restaurantList;
    }
}
