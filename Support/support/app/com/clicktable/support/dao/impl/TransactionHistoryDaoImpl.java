package com.clicktable.support.dao.impl;

import org.springframework.stereotype.Component;

import com.clicktable.support.dao.intf.TransactionHistoryDao;
import com.clicktable.support.model.TransactionHistory;

@Component
public class TransactionHistoryDaoImpl extends JPADao<TransactionHistory> implements TransactionHistoryDao{

}
