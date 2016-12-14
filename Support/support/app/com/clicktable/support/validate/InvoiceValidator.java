package com.clicktable.support.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.clicktable.support.model.Invoice;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.EntityValidator;
import com.clicktable.validate.ValidationError;

@org.springframework.stereotype.Service
public class InvoiceValidator extends EntityValidator<Invoice> {

    public List<ValidationError> validateInvoiceOnAdd(Invoice invoice) {
        List<ValidationError> errorList = validateOnAdd(invoice);

        if (!errorList.isEmpty()) {
            errorList.addAll(validateEnumValues(invoice, SupportConstants.INVOICE));
        }
        /*if ((invoice.getStatus() != null && !UtilityMethods.getEnumValues(EmpowerConstants.INVOICE, STATUS)
				.contains(invoice.getStatus()))) {
			errorList = CustomValidations.populateErrorList(errorList,
					STATUS,
					UtilityMethods.getErrorMsg(ErrorCodes.INVALID_TAX_STATUS),
					ErrorCodes.INVALID_TAX_STATUS);
		}

		if ((invoice.getCurrency() != null &&!UtilityMethods.getEnumValues(EmpowerConstants.INVOICE,
				CURRENCY).contains(invoice.getCurrency()))) {
			errorList = CustomValidations.populateErrorList(errorList,
					CURRENCY, UtilityMethods
							.getErrorMsg(ErrorCodes.INVALID_INVOICE_CURRENCY),
					ErrorCodes.INVALID_INVOICE_CURRENCY);
		}

		if ((invoice.getInvToCountry() != null && !UtilityMethods.getEnumValues(EmpowerConstants.INVOICE,
				EmpowerConstants.INVOICE_COUNTRY).contains(invoice.getInvToCountry()))) {
			errorList = CustomValidations.populateErrorList(errorList,
					EmpowerConstants.INVOICE_COUNTRY, UtilityMethods
							.getErrorMsg(ErrorCodes.INVALID_INVOICE_COUNTRY),
					ErrorCodes.INVALID_INVOICE_COUNTRY);
		}

		if ((invoice.getInvToState() != null && !UtilityMethods.getEnumValues(EmpowerConstants.INVOICE, EmpowerConstants.INVOICE_STATE)
				.contains(invoice.getInvToState()))) {
			errorList = CustomValidations.populateErrorList(errorList,
					STATE, UtilityMethods
							.getErrorMsg(ErrorCodes.INVALID_INVOICE_STATE),
					ErrorCodes.INVALID_INVOICE_STATE);
		}*/

        return errorList;
    }

    public Map<String, Object> validateParamsInvoicePdf(Map<String, Object> params, ArrayList<ValidationError> errorList) {
        Map<String, Object> validParams = validateFinderParams(params, Invoice.class);
        if (!validParams.containsKey(SupportConstants.INVOICE_ID)) {
            errorList.add(new ValidationError(SupportConstants.INVOICE, UtilityMethods.getErrorMsg(ErrorCodes.INVOICE_ID_REQUIRED), ErrorCodes.INVOICE_ID_REQUIRED));
        }
        return validParams;
    }

}
