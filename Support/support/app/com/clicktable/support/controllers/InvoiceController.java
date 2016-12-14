package com.clicktable.support.controllers;

import java.io.File;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.clicktable.model.Section;
import com.clicktable.response.BaseResponse;
import com.clicktable.response.ErrorResponse;
import com.clicktable.response.GetResponse;
import com.clicktable.response.SupportResponse;
import com.clicktable.support.exception.ServiceValidationException;
import com.clicktable.support.model.Invoice;
import com.clicktable.support.service.intf.InvoiceService;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.SupportResponseCodes;
import com.clicktable.support.validate.InvoiceValidator;
import com.clicktable.util.ErrorCodes;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.ValidationError;
import com.fasterxml.jackson.databind.JsonNode;

/**
 */
@org.springframework.stereotype.Controller
public class InvoiceController extends Controller {
    private static final Logger.ALogger log = Logger.of(InvoiceController.class);
    @Autowired
    InvoiceValidator invoiceValidator;
    @Autowired
    private InvoiceService invoiceService;

    public Result getInvoice() {
        Map<String, Object> params = UtilityMethods
                .convertQueryStringToMap(request().queryString());
        log.info(params.toString());
        List<Invoice> invoices = invoiceService.getInvoice(params);
        BaseResponse response = new GetResponse<Invoice>(SupportResponseCodes.INVOICE_FETCHED_SUCCESSFULLY, invoices);

        return ok(Json.toJson(response));
    }

    public Result getInvoicePdf() {
        ArrayList<ValidationError> errorList = new ArrayList<ValidationError>();
        Map<String, Object> params = UtilityMethods.convertQueryStringToMap(request().queryString());

        Map<String, Object> validParams = invoiceValidator.validateParamsInvoicePdf(params, errorList);

        if (errorList.isEmpty()) {
            try {
                String path = SupportConstants.PATH;
                Invoice invoice = invoiceService.findInvoice(validParams);
                String restaurantGuid = invoice.getRestaurantGuid();
                String month = new DateFormatSymbols().getMonths()[invoice.getFinMonth() - 1];
                String year = invoice.getFinYear().toString();

                File file = new File(path + "//" + SupportConstants.INVOICE_FOLDER + "//" + year + "-" + month + "-" + restaurantGuid + ".pdf");
                log.debug("File to export {}", file.toString());

                if (file.exists()) {
                    SupportResponse<File> fileResponse = new SupportResponse<File>(SupportResponseCodes.INVOICE_FILE_FETCH_SUCCESS, file);
                    response().setContentType("application/x-download");
                    return ok(fileResponse.getObj());
                } else {
                    errorList.add(new ValidationError(SupportConstants.FILE_NOT_FOUND, UtilityMethods.getErrorMsg(ErrorCodes.FILE_NOT_GNRTD), ErrorCodes.FILE_NOT_GNRTD));
                    JsonNode result = Json.toJson(new ErrorResponse(SupportResponseCodes.INVOICE_FILE_FETCH_FAILURE, errorList));
                    return ok(result);
                }
            } catch (ServiceValidationException e) {
                log.info(e.getMessage());
                JsonNode result = Json.toJson(new ErrorResponse(SupportResponseCodes.INVOICE_FILE_FETCH_FAILURE, e.getErrorList()));
                return ok(result);
            }
        }
        JsonNode result = Json.toJson(new ErrorResponse(SupportResponseCodes.INVOICE_FILE_FETCH_FAILURE, errorList));
        return ok(result);

    }
}
