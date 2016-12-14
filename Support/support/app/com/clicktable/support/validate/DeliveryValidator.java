package com.clicktable.support.validate;

import java.util.List;

import com.clicktable.support.model.Delivery;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.CustomValidations;
import com.clicktable.validate.EntityValidator;
import com.clicktable.validate.ValidationError;

@org.springframework.stereotype.Service
public class DeliveryValidator extends EntityValidator<Delivery> {

    public List<ValidationError> validateDeliveryOnUpdate(Delivery delivery) {
        List<ValidationError> errorList = validateOnAdd(delivery);
        if ((!UtilityMethods.getEnumValues(SupportConstants.DELIVERY_LABEL, SupportConstants.SMS_STATUS).contains(delivery.getStatus())) && (delivery.getStatus() != null)) {
            errorList = CustomValidations.populateErrorList(errorList, SupportConstants.SMS_STATUS, UtilityMethods.getErrorMsg(ErrorCodes.INVALID_SMS_STATUS), ErrorCodes.INVALID_SMS_STATUS);
        }
        if ((!UtilityMethods.getEnumValues(SupportConstants.DELIVERY_LABEL, SupportConstants.SMS_STATUS_CAUSE).contains(delivery.getCause())) && (delivery.getCause() != null)) {
            errorList = CustomValidations.populateErrorList(errorList, SupportConstants.SMS_STATUS_CAUSE, UtilityMethods.getErrorMsg(ErrorCodes.INVALID_SMS_STATUS_CAUSE), ErrorCodes.INVALID_SMS_STATUS_CAUSE);
        }
        return errorList;
    }

}
