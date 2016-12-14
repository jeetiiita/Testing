package com.clicktable.support.service.impl;

import com.ccavenue.security.AesCryptUtil;
import com.clicktable.response.BaseResponse;
import com.clicktable.response.ErrorResponse;
import com.clicktable.support.dao.intf.InvoiceDao;
import com.clicktable.support.dao.intf.PaymentDao;
import com.clicktable.support.exception.ServiceValidationException;
import com.clicktable.support.model.Invoice;
import com.clicktable.support.model.Payment;
import com.clicktable.support.service.intf.InvoicePdfService;
import com.clicktable.support.service.intf.PaymentService;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.SupportResponseCodes;
import com.clicktable.support.validate.PaymentValidator;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.CustomValidations;
import com.clicktable.validate.ValidationError;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import play.Logger;

import javax.transaction.Transactional;
import java.net.URLDecoder;
import java.util.*;
import java.util.Map.Entry;

@Component
public class PaymentServiceImpl implements PaymentService {

    private static final Logger.ALogger log = Logger.of(PaymentServiceImpl.class);
    @Autowired
    InvoicePdfService invoicePdfService;
    @Autowired
    private PaymentValidator paymentValidator;
    @Autowired
    private PaymentDao paymentDao;
    @Autowired
    private InvoiceDao invoiceDao;

    @Override
    public Payment create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Transactional
    @Override
    public Payment update(Payment payment) {
        log.info(payment.toString());
        List<ValidationError> errorList = paymentValidator.validatePaymentOnAdd(payment);
        Payment dbPayment = null;
        if (errorList.isEmpty()) {
            dbPayment = paymentDao.find(Long.valueOf(payment.getOrderId()));
            copyFields(payment, dbPayment);
            paymentDao.update(dbPayment);
        } else {
            throw new ServiceValidationException("Payment validation failed", errorList);
        }
        return dbPayment;
    }

    private void copyFields(Payment payment, Payment dbPayment) {
        dbPayment.setOrderStatus(payment.getOrderStatus());
        dbPayment.setAmount(payment.getAmount());
        dbPayment.setCurrency(payment.getCurrency());
        dbPayment.setPaymentMode(payment.getPaymentMode());
        dbPayment.setBankRefNo(payment.getBankRefNo());
        dbPayment.setCardName(payment.getCardName());
        dbPayment.setFailureMessage(payment.getFailureMessage());
        dbPayment.setStatusCode(payment.getStatusCode());
        dbPayment.setStatusMessage(payment.getStatusMessage());
        dbPayment.setTrackingId(payment.getTrackingId());
        dbPayment.setUpdatedDate(DateTime.now().toDate());
    }

    @Override
    public BaseResponse encryptPaymentData(Invoice invoice, Payment payment) {
        BaseResponse response = null;
        List<ValidationError> listOfError = new ArrayList<ValidationError>();

        if (invoice != null) {

            if (!invoice.getStatus().equals(SupportConstants.CREATED)) {
                listOfError.add(new ValidationError(SupportConstants.STATUS, ErrorCodes.INVOICE_NOT_VALID_FOR_PAYMENT));
            } else {
                Map<String, Object> invoiceProps = convertParamsInvoice(invoice, payment);
                Map<String, Object> billingInfoProps = convertParamsBillingInfo(
                        UtilityMethods.introspect(invoice.getBillingInfo()));

                invoiceProps.putAll(billingInfoProps);

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("encRequest", getEncryptedData(invoiceProps));
                params.put("accessCode", UtilityMethods.getConfString("ccavenue.accessCode"));
                params.put("ccAvenueUrl", UtilityMethods.getConfString("ccavenue.url"));
                response = new BaseResponse(SupportResponseCodes.USER_DETAILS_ENCRYPTED_SUCCESSFULLY, true, params);
            }
        } else {
            listOfError = CustomValidations.populateErrorList(listOfError, "id",
                    UtilityMethods.getErrorMsg(ErrorCodes.ID_DOES_NOT_EXIST), ErrorCodes.ID_DOES_NOT_EXIST);
            response = new ErrorResponse(SupportResponseCodes.USER_DETAILS_ENCRYPTION_FAILURE, listOfError);
            response = new ErrorResponse(SupportResponseCodes.USER_DETAILS_ENCRYPTION_FAILURE, listOfError);
        }
        return response;
    }

    private Map<String, Object> convertParamsInvoice(Invoice invoice, Payment payment) {
        Map<String, Object> map = new HashMap<>();
        map.put("order_id", payment.getId());
        if (invoice.getNetAmount() != null) {
            map.put("amount", invoice.getNetAmount());
        }
        if (invoice.getCurrency() != null) {
            map.put("currency", invoice.getCurrency());
        }
        map.put("merchant_id", UtilityMethods.getConfString("ccavenue.merchantId"));
        return map;
    }

    private Map<String, Object> convertParamsBillingInfo(Map<String, Object> map) {
        //TODO: Use Object instead of Map
        if (map.containsKey("zipCode")) {
            map.put("billing_zip", map.get("zipCode"));
            map.remove("zipCode");
        }
        if (map.containsKey("legalName")) {
            map.put("billing_name", map.get("legalName"));
            map.remove("legalName");
        }
        if (map.containsKey("state")) {
            map.put("billing_state", map.get("state"));
            map.remove("state");
        }
        if (map.containsKey("city")) {
            map.put("billing_city", map.get("city"));
            map.remove("city");
        }
        if (map.containsKey("country")) {
            map.put("billing_country", map.get("country"));
            map.remove("country");
        }
        if (map.containsKey("phone")) {
            map.put("billing_tel", map.get("phone"));
            map.remove("phone");
        }
        if (map.containsKey("email")) {
            map.put("billing_email", map.get("email"));
            map.remove("email");
        }
        if (map.containsKey("address1") && map.containsKey("address2")) {
            map.put("billing_address", map.get("address1").toString() + " " + map.get("address2").toString());
            map.remove("address1");
            map.remove("address2");
        } else if (map.containsKey("address1")) {
            map.put("billing_address", map.get("address1").toString());
            map.remove("address1");
        }
        return map;
    }

    private String getEncryptedData(Map<String, Object> propMap) {
        StringBuilder params = new StringBuilder();

        for (Entry<String, Object> entry : propMap.entrySet()) {
            params.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        AesCryptUtil aesUtil = new AesCryptUtil(UtilityMethods.getConfString("ccavenue.workingKey"));
        String encRequest = aesUtil.encrypt(params.toString());
        return encRequest;
    }

    @Override
    public JsonNode decryptResponse(String encResponse) {
        AesCryptUtil aesUtil = new AesCryptUtil(UtilityMethods.getConfString("ccavenue.workingKey"));
        String decResp = aesUtil.decrypt(encResponse);
        log.info(decResp);
        StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jNode = mapper.createObjectNode();

        String pair = null, pname = null, pvalue = null;
        while (tokenizer.hasMoreTokens()) {
            pair = (String) tokenizer.nextToken();
            if (pair != null) {
                StringTokenizer strTok = new StringTokenizer(pair, "=");
                pname = "";
                pvalue = "";
                if (strTok.hasMoreTokens()) {
                    pname = (String) strTok.nextToken();
                    if (strTok.hasMoreTokens())
                        pvalue = (String) strTok.nextToken();
                    jNode.put(pname, URLDecoder.decode(pvalue));
                }
            }
        }

        return jNode;
    }


}
