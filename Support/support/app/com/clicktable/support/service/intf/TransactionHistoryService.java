package com.clicktable.support.service.intf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clicktable.support.model.TransactionHistory;

@Service
public interface TransactionHistoryService {

	TransactionHistory addTransactionHistory(TransactionHistory transaction);

	List<TransactionHistory> getTransactionHistory(Map<String, Object> params);

}
