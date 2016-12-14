/**
 *
 */
package com.clicktable.support.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clicktable.support.dao.intf.SingleTaxDao;
import com.clicktable.support.model.TaxLayer;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.CustomValidations;
import com.clicktable.validate.EntityValidator;
import com.clicktable.validate.ValidationError;

/**
 * @author j.yadav
 */
@Service
public class TaxLayerValidator extends EntityValidator<TaxLayer> {

    @Autowired
    private SingleTaxDao singleTaxDao;

    public List<ValidationError> validateTaxLayer(Set<TaxLayer> taxLayers) {

        List<ValidationError> errorList = new ArrayList<ValidationError>();

        for (TaxLayer taxLayer : taxLayers) {
            errorList.addAll(validateOnAdd(taxLayer));
        }

        if (errorList.isEmpty()) {

            for (TaxLayer taxLayer : taxLayers) {

                if (taxLayer.getSingleTax() == null || singleTaxDao.find(taxLayer.getSingleTax().getId()) == null) {
                    errorList = CustomValidations.populateErrorList(errorList, "singleTax",
                            UtilityMethods.getErrorMsg(ErrorCodes.ID_DOES_NOT_EXIST), ErrorCodes.ID_DOES_NOT_EXIST);
                }

                Integer layerNumber = taxLayer.getLayerNumber();
                int size = taxLayer.getApplicableLayers().size();

                if (layerNumber <= taxLayer.getApplicableLayers().get(size - 1)) {
                    errorList = CustomValidations.populateErrorList(errorList, "applicableLayer",
                            UtilityMethods.getErrorMsg(ErrorCodes.INVALID_APPLICABLE_LAYER),
                            ErrorCodes.INVALID_APPLICABLE_LAYER);
                    break;

                }

            }
        }

        return errorList;
    }

}
