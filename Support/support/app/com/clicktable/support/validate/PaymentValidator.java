package com.clicktable.support.validate;

import java.util.List;

import com.clicktable.support.model.Payment;
import com.clicktable.validate.EntityValidator;
import com.clicktable.validate.ValidationError;

@org.springframework.stereotype.Service
public class PaymentValidator extends EntityValidator<Payment> {

    public List<ValidationError> validatePaymentOnAdd(
            Payment payment) {
        List<ValidationError> errorList = validateOnAdd(payment);

        return errorList;
    }
}
