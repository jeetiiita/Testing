/**
 *
 */
package com.clicktable.support.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clicktable.support.exception.ServiceValidationException;
import com.clicktable.support.dao.intf.SingleTaxDao;
import com.clicktable.support.model.AggregateTax;
import com.clicktable.support.model.SingleTax;
import com.clicktable.support.service.intf.SingleTaxService;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.CustomValidations;
import com.clicktable.validate.ValidationError;

/**
 * @author j.yadav
 */
@Component
public class SingleTaxServiceImpl implements SingleTaxService {

    @Autowired
    private SingleTaxDao singleTaxDao;

    @Override
    public SingleTax create(SingleTax singleTax) {
        SingleTax singleTaxEntity = singleTaxDao.create(singleTax);
        return singleTaxEntity;
    }

    @Override
    public List<SingleTax> getSingleTaxes(Map<String, Object> params) {
        List<SingleTax> singleTaxList = singleTaxDao.findByFields(SingleTax.class, params);
        return singleTaxList;
    }

    @Override
    public boolean disable(Integer id) {
        List<ValidationError> errorList = new ArrayList<ValidationError>();
        SingleTax singleTax = singleTaxDao.find(id);

        if (singleTax == null) {
            errorList = CustomValidations.populateErrorList(errorList, SupportConstants.ID,
                    UtilityMethods.getErrorMsg(ErrorCodes.ID_DOES_NOT_EXIST), ErrorCodes.ID_DOES_NOT_EXIST);
            throw new ServiceValidationException("Exception in SingleTax disable Function", errorList);
        } else {
            List<Integer> taxList = singleTaxDao.getAggregateTaxForSingleTax(singleTax);
            if (taxList.isEmpty()) {
                singleTax.setStatus(SupportConstants.INACTIVE_STATUS);
                singleTax.setUpdatedDate(DateTime.now().toDate());
                singleTaxDao.update(singleTax);
            } else {
                StringBuilder str = new StringBuilder();
                for(Integer aggregateId : taxList) {
                    str.append(aggregateId + ", ");
                }
                errorList = CustomValidations.populateErrorList(errorList, "aggregateTaxIds",
                       str.toString(), ErrorCodes.SINGLE_TAX_ATTACHED_WITH_AGGRGATETAX);
                throw new ServiceValidationException("Exception in SingleTax Disable Function: ", errorList);

            }

        }
        return true;
    }

}
