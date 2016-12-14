package com.clicktable.support.controllers;

import com.clicktable.response.BaseResponse;
import com.clicktable.response.ErrorResponse;
import com.clicktable.response.UpdateResponse;
import com.clicktable.support.exception.ServiceValidationException;
import com.clicktable.support.model.Invoice;
import com.clicktable.support.model.Payment;
import com.clicktable.support.service.intf.InvoiceService;
import com.clicktable.support.service.intf.PaymentService;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.SupportResponseCodes;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.CustomValidations;
import com.clicktable.validate.ValidationError;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
public class PaymentController extends Controller {

    public static final String ID = "id";
    private static final Logger.ALogger log = Logger.of(PaymentController.class);
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private InvoiceService invoiceService;

    public Result updatePaymentDetail() {
        log.info("Updating payment");
        JsonNode jsonNode = request().body().asJson();
        Map<String, List<String>> params = Json.fromJson(jsonNode, Map.class);
        BaseResponse response;
        JsonNode json = paymentService.decryptResponse(params.get("encResp").get(0));
        Payment payment = Json.fromJson(json, Payment.class);
        try {
            payment = paymentService.update(payment);
            if (null != payment && SupportConstants.PAYMENT_SUCCESS.equalsIgnoreCase(payment.getOrderStatus())) {
                invoiceService.updateInvoice(payment);
                response = new UpdateResponse<Payment>(SupportResponseCodes.PAYMENT_SUCCESSFULL, payment.getId().toString());
            } else {
                response = new UpdateResponse<Payment>(SupportResponseCodes.PAYMENT_FAILED, "");
            }
        } catch (ServiceValidationException se) {
            log.warn("Validation exception in update invoice", se);
            response = new ErrorResponse(SupportResponseCodes.INVOICE_UPDATION_FAILURE, se.getErrorList());
        }

        JsonNode result = Json.toJson(response);
        return ok(result);
    }

    public Result startPayment() {
        log.info("Starting payment");
        JsonNode jsonNode = request().body().asJson();
        Map<String, List<String>> params = Json.fromJson(jsonNode, Map.class);
        BaseResponse response;
        if (params.get(ID) == null || params.get(ID).get(0) == null) {
            List<ValidationError> listOfError = new ArrayList<>();
            listOfError = CustomValidations.populateErrorList(listOfError, "id",
                    UtilityMethods.getErrorMsg(ErrorCodes.ID_MISSING), ErrorCodes.ID_MISSING);
            response = new ErrorResponse(SupportResponseCodes.USER_DETAILS_ENCRYPTION_FAILURE, listOfError);
        } else {
            Long id = Long.parseLong(String.valueOf(params.get(ID).get(0)));
            Invoice invoice = invoiceService.getInvoiceById(id);
            Payment payment = paymentService.create(makePaymentObject(invoice, params));
            payment.setOrderId(String.valueOf(payment.getId()));
            response = paymentService.encryptPaymentData(invoice, payment);
        }
        JsonNode result = Json.toJson(response);
        if (log.isDebugEnabled()) {
            log.debug(Json.stringify(result));
        }
        return ok(result);
    }

    private Payment makePaymentObject(Invoice invoice, Map<String, List<String>> params) {
        Payment payment = new Payment();
        payment.setInvoice(invoice);
        payment.setCreatedBy(String.valueOf(params.get("createdBy")));
        payment.setUpdatedBy(String.valueOf(params.get("updatedBy")));
        payment.setOrderStatus(Payment.STATUS_PROCESSING);
        return payment;
    }
}