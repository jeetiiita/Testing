/**
 *
 */
package com.clicktable.support.validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clicktable.support.dao.intf.AggregateTaxDao;
import com.clicktable.support.model.AggregateTax;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.CustomValidations;
import com.clicktable.validate.EntityValidator;
import com.clicktable.validate.ValidationError;

/**
 * @author j.yadav
 */
@Service
public class AggregateTaxValidator extends EntityValidator<AggregateTax> {

    @Autowired
    private AggregateTaxDao aggregateTaxDao;

    @Autowired
    private TaxLayerValidator taxLayerValidator;

    public List<ValidationError> validateTaxOnAdd(AggregateTax aggregateTax) {

        List<ValidationError> errorList = validateOnAdd(aggregateTax);

        if (errorList.isEmpty() && aggregateTax.getCentralTax() == false && aggregateTax.getState() == null) {
            errorList = CustomValidations.populateErrorList(errorList,
                    SupportConstants.STATE, UtilityMethods
                            .getErrorMsg(ErrorCodes.TAX_STATE_REQUIRED),
                    ErrorCodes.TAX_STATE_REQUIRED);
        }

        if (errorList.isEmpty()) {
            Map<String, Object> params = new HashMap<String, Object>();

            params.put(SupportConstants.NAME, aggregateTax.getName());
            params.put(SupportConstants.STATUS, SupportConstants.ACTIVE);

            List<AggregateTax> taxList = aggregateTaxDao.findByFields(
                    AggregateTax.class, params);
            if (!taxList.isEmpty()) {
                errorList = CustomValidations.populateErrorList(errorList,
                        SupportConstants.NAME, UtilityMethods
                                .getErrorMsg(ErrorCodes.DUPLICATE_TAX_NAME),
                        ErrorCodes.DUPLICATE_TAX_NAME);
            } else {
                errorList = taxLayerValidator.validateTaxLayer(aggregateTax
                        .getTaxLayers());
            }
        }
        return errorList;
    }

}
