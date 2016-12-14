package com.clicktable.support.dao.impl;

import org.springframework.stereotype.Component;

import com.clicktable.support.dao.intf.StateDao;
import com.clicktable.support.model.State;

@Component
public class StateDaoImpl extends JPADao<State> implements StateDao {


}
