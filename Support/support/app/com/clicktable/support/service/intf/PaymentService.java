package com.clicktable.support.service.intf;

import com.clicktable.response.BaseResponse;
import com.clicktable.support.model.Invoice;
import com.clicktable.support.model.Payment;
import com.fasterxml.jackson.databind.JsonNode;

@org.springframework.stereotype.Service
public interface PaymentService {

    Payment create(Payment payment);

    Payment update(Payment payment);

    JsonNode decryptResponse(String response);

    /**
     * @param params
     * @param payment
     * @return
     */
    BaseResponse encryptPaymentData(Invoice params, Payment payment);
}
