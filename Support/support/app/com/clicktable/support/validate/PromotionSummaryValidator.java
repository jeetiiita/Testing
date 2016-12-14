package com.clicktable.support.validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.clicktable.support.model.PromotionSummary;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.util.Constants;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.EntityValidator;
import com.clicktable.validate.ValidationError;

import play.i18n.Messages;

@Service
public class PromotionSummaryValidator extends EntityValidator<PromotionSummary> {

    public Map<String, Object> validateParams(Map<String, Object> paramMap, List<ValidationError> errorList) {

        Map<String, Object> params = new HashMap<String, Object>();
        if (paramMap.containsKey(Constants.START_DATE)) {

            if (paramMap.containsKey(Constants.END_DATE)) {
                DateTime startDate = getValidDate(paramMap.get(Constants.START_DATE).toString(), Constants.START_DATE, errorList);
                DateTime endDate = getValidDate(paramMap.get(Constants.END_DATE).toString(), Constants.END_DATE, errorList);

                if (errorList.isEmpty()) {
                    if (endDate.isBefore(startDate)) {
                        errorList.add(new ValidationError(Constants.END_DATE, UtilityMethods.getErrorMsg(com.clicktable.util.ErrorCodes.INVALID_END_DATE), com.clicktable.util.ErrorCodes.INVALID_END_DATE));
                    }
                    params.put(Constants.START_DATE, startDate);
                    params.put(Constants.END_DATE, endDate);
                }
            } else {
                errorList.add(new ValidationError(Constants.END_DATE, Messages.get(ErrorCodes.REQUIRED, Constants.END_DATE), ErrorCodes.REQUIRED));

            }

        } else {

            errorList.add(new ValidationError(Constants.START_DATE, Messages.get(ErrorCodes.REQUIRED, Constants.START_DATE), ErrorCodes.REQUIRED));

        }

        if (paramMap.containsKey(SupportConstants.REST_GUID)) {
            params.put(SupportConstants.REST_GUID, paramMap.get(SupportConstants.REST_GUID));
        }

        return params;
    }

    private DateTime getValidDate(String dateStr, String fieldName, List<ValidationError> errorList) {
        DateTime date = null;
        try {
            date = DateTime.parse(dateStr);
        } catch (Exception e) {
            errorList.add(new ValidationError(fieldName, e.getMessage(), com.clicktable.util.ErrorCodes.INVALID_DATE_FORMAT));
        }
        return date;
    }

}
