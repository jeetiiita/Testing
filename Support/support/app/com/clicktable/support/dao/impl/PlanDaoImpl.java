package com.clicktable.support.dao.impl;

import org.springframework.stereotype.Component;

import com.clicktable.support.dao.intf.PlanDao;
import com.clicktable.support.model.Plan;

@Component
public class PlanDaoImpl extends JPADao<Plan> implements PlanDao {

}
