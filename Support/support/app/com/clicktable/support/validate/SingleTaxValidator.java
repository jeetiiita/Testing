package com.clicktable.support.validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.clicktable.support.dao.intf.SingleTaxDao;
import com.clicktable.support.model.SingleTax;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.CustomValidations;
import com.clicktable.validate.EntityValidator;
import com.clicktable.validate.ValidationError;

@org.springframework.stereotype.Service
public class SingleTaxValidator extends EntityValidator<SingleTax> {

    @Autowired
    private SingleTaxDao singleTaxDao;

    public List<ValidationError> validateTaxOnAdd(SingleTax singleTax) {

        List<ValidationError> errorList = validateOnAdd(singleTax);

        Map<String, Object> params = new HashMap<String, Object>();

        params.put(SupportConstants.STATE, singleTax.getState());
        params.put(SupportConstants.COUNTRY, singleTax.getCountry());
        params.put(SupportConstants.NAME, singleTax.getName());
        params.put(SupportConstants.STATUS, SupportConstants.ACTIVE);

        List<SingleTax> taxList = singleTaxDao.findByFields(SingleTax.class, params);


        if (!taxList.isEmpty())
            errorList = CustomValidations.populateErrorList(errorList, SupportConstants.NAME, UtilityMethods.getErrorMsg(ErrorCodes.DUPLICATE_TAX_NAME), ErrorCodes.DUPLICATE_TAX_NAME);

        if (singleTax.getCentralTax() == false && singleTax.getState() == null) {
            errorList = CustomValidations.populateErrorList(errorList,
                    SupportConstants.STATE, UtilityMethods
                            .getErrorMsg(ErrorCodes.TAX_STATE_REQUIRED),
                    ErrorCodes.TAX_STATE_REQUIRED);
        }


        return errorList;
    }
}
