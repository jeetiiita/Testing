package com.clicktable.support.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clicktable.support.dao.intf.TransactionHistoryDao;
import com.clicktable.support.model.TransactionHistory;
import com.clicktable.support.service.intf.TransactionHistoryService;

@Component
public class TransactionHistoryServiceImpl implements TransactionHistoryService{

	@Autowired
	TransactionHistoryDao transactionDao;
	
	@Override
	public TransactionHistory addTransactionHistory(TransactionHistory transaction){
		TransactionHistory transactionEntity = transactionDao.create(transaction);
		return transactionEntity;
	}
	
	@Override
	public List<TransactionHistory> getTransactionHistory(Map<String, Object> params){
		List<TransactionHistory> transactions = transactionDao.findByFields(TransactionHistory.class, params);
		return transactions;
	}
}
