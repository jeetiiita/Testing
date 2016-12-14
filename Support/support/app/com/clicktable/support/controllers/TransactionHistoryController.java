package com.clicktable.support.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.clicktable.response.BaseResponse;
import com.clicktable.response.ErrorResponse;
import com.clicktable.response.GetResponse;
import com.clicktable.response.PostResponse;
import com.clicktable.support.model.TransactionHistory;
import com.clicktable.support.service.intf.TransactionHistoryService;
import com.clicktable.support.util.SupportResponseCodes;
import com.clicktable.support.validate.TransactionHistoryValidator;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.ValidationError;
import com.fasterxml.jackson.databind.JsonNode;

@org.springframework.stereotype.Controller
public class TransactionHistoryController extends Controller{

	@Autowired
	TransactionHistoryValidator transactionValidator;
	
	@Autowired
	TransactionHistoryService transactionService;
	
	public Result addTransactionHistory(){
		BaseResponse response = null;
		TransactionHistory transaction = Json.fromJson(request().body().asJson(), TransactionHistory.class);
		List<ValidationError> errorList = transactionValidator.validateOnAdd(transaction);

		if(errorList.isEmpty()){
			TransactionHistory transactionEntity = transactionService.addTransactionHistory(transaction);
			response = new PostResponse<TransactionHistory>(SupportResponseCodes.TXN_HISTORY_ADDITION_SUCCESSFUL, transactionEntity.getId().toString());
		}else{
			response = new ErrorResponse(SupportResponseCodes.TXN_HISTORY_ADDITION_FAILURE, errorList);
		}
		
		JsonNode result = Json.toJson(response);
        return ok(result);
	}
	
	public Result getTransactionHistory(){
		BaseResponse response = null;
		Map<String, Object> params = UtilityMethods.convertQueryStringToMap(request().queryString());
		
		Map<String, Object> validParams = transactionValidator.validateFinderParams(params, TransactionHistory.class);
		List<TransactionHistory> transactions = transactionService.getTransactionHistory(validParams);
		response = new GetResponse<TransactionHistory>(SupportResponseCodes.TXN_HISTORY_FETCH_SUCCESSFUL, transactions);
		
		JsonNode result = Json.toJson(response);
        return ok(result);
	}
	
}
