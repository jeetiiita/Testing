package com.clicktable.support.service.impl;

import com.clicktable.model.Restaurant;
import com.clicktable.support.dao.intf.InvoiceDao;
import com.clicktable.support.dao.intf.PaymentDao;
import com.clicktable.support.exception.ServiceValidationException;
import com.clicktable.support.factory.InvoiceLineServiceFactory;
import com.clicktable.support.model.*;
import com.clicktable.support.service.intf.*;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.validate.InvoiceValidator;
import com.clicktable.util.Constants;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.CustomValidations;
import com.clicktable.validate.ValidationError;
import com.fasterxml.jackson.databind.JsonNode;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import play.Logger;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.ws.WS;

import javax.transaction.Transactional;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Component
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger.ALogger log = Logger.of(InvoiceServiceImpl.class);

    @Autowired
    private RestaurantPlanService restaurantPlanService;

    @Autowired
    private PlanService planService;

    @Autowired
    private NotificationService notification;

    @Autowired
    private InvoiceLineServiceFactory invoiceLineServiceFactory;

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private InvoiceValidator invoiceValidator;

    @Autowired
    private InvoicePdfService invoicePdfService;

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public List<Invoice> getInvoice(Map<String, Object> params) {
        List<Invoice> invoiceList = invoiceDao.findByFields(Invoice.class, params);
        Map<String, Object> validParams = new HashMap<>();
        for (Invoice invoice : invoiceList) {
            if (!(invoice == null) && SupportConstants.PAID.equals(invoice.getStatus())) {
                validParams.put(SupportConstants.INVOICE_ID, invoice.getInvoiceId());
                List<Payment> payments = paymentDao.findByFields(Payment.class, validParams);
                for (Payment payment : payments) {
                    if (SupportConstants.PAYMENT_SUCCESS.equalsIgnoreCase(payment.getOrderStatus())) {
                        invoice.setPaymentDate(payment.getUpdatedDate());
                        invoice.setPaymentMode(payment.getPaymentMode());
                    }
                }
            }
        }
        return invoiceList;
    }

    @Override
    public Invoice create(Invoice invoice) {
        return invoiceDao.create(invoice);
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceDao.find(id);
    }

    @Override
    @Transactional
    public void generateInvoice(Restaurant restaurant, Integer bookNumber, String token) {
        Invoice invoice = new Invoice();
        invoice.setBookNumber(bookNumber);
        invoice.setInvoiceId(Long.parseLong(generateInvoiceId(bookNumber)));
        invoice.setRestaurantGuid(restaurant.getGuid());

        invoice.setCreatedBy(token);
        invoice.setUpdatedBy(token);

        setInvoiceDates(invoice);

        InvoiceBillingInfo billingInfo = new InvoiceBillingInfo(restaurant);
        invoice.setBillingInfo(billingInfo);

        // generateInvoiceLine For Items if plan exist
        generateAndSetInvoiceLines(restaurant, invoice, token);
    }

    private void setInvoiceDates(Invoice invoice) {
        invoice.setFinMonth(DateTime.now().minusMonths(1).getMonthOfYear());
        invoice.setFinYear(DateTime.now().getYear());
        invoice.setInvoiceDate(DateTime.now().withTime(0, 0, 0, 0).toDate());

        int dueDateMonth = Integer.parseInt(UtilityMethods.getConfString(SupportConstants.INVOICE_DUE_DATE));
        invoice.setDueDate(DateTime.now().plusMonths(dueDateMonth).withTime(0, 0, 0, 0).toDate());

    }

    private void generateAndSetInvoiceLines(Restaurant restaurant, Invoice invoice, String token) {
        RestaurantPlan restaurantPlan = restaurantPlanService.getRestaurantPlanInvoice(restaurant);

        if (restaurantPlan != null) {
            Set<PlanItem> planItems = planService.getPlanItems(restaurantPlan.getPlan());

            List<InvoiceLine> invoiceLineList = new ArrayList<InvoiceLine>();
            Integer lineNumber = 1;
            Date planStartDate = restaurantPlan.getStartDate();
            for (PlanItem planItem : planItems) {

                InvoiceLineService invoiceLineService = invoiceLineServiceFactory
                        .getInvoiceLineService(planItem.getItem().getType());
                InvoiceLine invoiceLine =  invoiceLineService.generateInvoiceLine(restaurant, planItem, lineNumber,
                        token,planStartDate);
                lineNumber += 1;
                invoiceLine.setInvoice(invoice);
                invoiceLineList.add(invoiceLine);
            }

            invoice.setInvoiceLines(invoiceLineList);

            setInvoiceAmounts(invoice);
            List<ValidationError> errorList = invoiceValidator.validateOnAdd(invoice);
            if (errorList.isEmpty()) {
                create(invoice);
                invoicePdfService.generateInvoicePdf(invoice);
            } else {
                throw new ServiceValidationException("Exception in InvoiceValidation " + errorList.toString(),
                        errorList);
            }
        } else {
            throw new ServiceValidationException("Exception in Resatarunt Plan");
        }
    }

    private void setInvoiceAmounts(Invoice invoice) {
        float basicAmount = 0f;
        float taxAmount = 0f;
        float totalAmount = 0f;
        for (InvoiceLine invoiceLine : invoice.getInvoiceLines()) {
            basicAmount += invoiceLine.getBasicAmount().floatValue();
            taxAmount += invoiceLine.getTaxAmount().floatValue();
            totalAmount += invoiceLine.getTotalAmount().floatValue();
        }

        BigDecimal basicAmt = BigDecimal.valueOf(basicAmount).setScale(2, BigDecimal.ROUND_HALF_DOWN);

        BigDecimal taxAmt = BigDecimal.valueOf(taxAmount).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal totalAmt = BigDecimal.valueOf(totalAmount).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal netAmount = BigDecimal.valueOf(totalAmount).setScale(0, BigDecimal.ROUND_HALF_DOWN);

        invoice.setBasicAmount(basicAmt);
        invoice.setTaxAmount(taxAmt);
        invoice.setTotalAmount(totalAmt);
        invoice.setNetAmount(netAmount.intValue());

        // multiply by -1 because values are returning in neagtion during
        // BigDecimal roundOff.
        float floatRoundOff = (totalAmount - netAmount.floatValue()) * -1;

        BigDecimal roundOff = BigDecimal.valueOf(floatRoundOff).setScale(2, BigDecimal.ROUND_HALF_DOWN);

        invoice.setRoundOffAmount(roundOff);

    }

    @Override
    public void generateAllInvoices(String token) {
        Promise<List<Restaurant>> restList = getAllActiveRestaurants(token);
        restList.onRedeem(restaurantList -> {
            generateInvoices(token, restaurantList);
        });

        restList.onFailure(t -> {
            throw t;
        });
    }

    @Transactional
    @Override
    public void updateInvoice(Payment payment) {
        log.debug("Updating invoice for Payment: {}", payment);
        Invoice invoice = payment.getInvoice();
        invoice.setStatus(SupportConstants.PAID);
        invoice.setUpdatedDate(new Timestamp(new Date().getTime()));
        Invoice updatedInvoice = invoiceDao.update(invoice);
        if (updatedInvoice != null && updatedInvoice.getStatus().equals(SupportConstants.PAID)) {
            invoicePdfService.generateInvoicePdf(updatedInvoice);
        }
    }

    private void generateInvoices(String token, List<Restaurant> restaurantList) {
        // Get all invoices generated for the month
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(SupportConstants.FIN_MONTH, DateTime.now().minusMonths(1).getMonthOfYear());
        params.put(SupportConstants.FIN_YEAR, DateTime.now().minusMonths(1).getYear());
        Integer maxBookNumber = invoiceDao.maxBookNumber(params);
        Integer bookNumber = 1;
        if (maxBookNumber >= bookNumber) {
            bookNumber = maxBookNumber + 1;
        }
        makePdfDirectory();
        List<String> failedResaurantInfo = new ArrayList<>();
        List<String> failedResaurantPlanAndTaxInfo = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            params.put(SupportConstants.FIN_MONTH, DateTime.now().minusMonths(1).getMonthOfYear());
            params.put(SupportConstants.FIN_YEAR, DateTime.now().minusMonths(1).getYear());
            params.put(SupportConstants.REST_GUID, restaurant.getGuid());
            List<Invoice> invoiceList = getInvoice(params);
            if (invoiceList.isEmpty()) {
                try {
                    generateInvoice(restaurant, bookNumber, token);
                    bookNumber += 1;
                } catch (ServiceValidationException se) {
                    log.error("Exception in generating Invoice:",se);
                    String restInfoStr = String.format("Restaurant Guid: %s legal Name: %s", restaurant.getGuid(),
                            restaurant.getLegalName() + se.getMessage());
                    failedResaurantPlanAndTaxInfo.add(restInfoStr);

                } catch (Exception e) {
                    log.error("Exception in generating invoice", e);
                    String restInfoStr = String.format("Restaurant Guid: %s legal Name: %s ", restaurant.getGuid(),
                            restaurant.getLegalName() + e.getMessage());
                    failedResaurantInfo.add(restInfoStr);
                }
            } else
                log.debug("Invoice is already Generated for restaurantGuid = " + restaurant.getGuid());
        }
        if (failedResaurantInfo.size() > 0) {
            sendFailedInvoiceMsgToAdmin(failedResaurantInfo);
        }
        if (failedResaurantPlanAndTaxInfo.size() > 0) {
            sendMissingPlanAndTaxNotificationToAdmin(failedResaurantPlanAndTaxInfo);
        }
    }

    private void makePdfDirectory() {
        String path = SupportConstants.PATH;
        File pdfFolder = new File(path + "//" + SupportConstants.INVOICE_FOLDER);
        boolean isFolderCreated = pdfFolder.mkdir();
        log.debug("Folder created : {}", isFolderCreated);
    }

    private void sendFailedInvoiceMsgToAdmin(List<String> failedResaurantInfo) {
        ArrayList<String> to = new ArrayList<>();
        ArrayList<String> tags = new ArrayList<>();
        to.add(UtilityMethods.getConfString(Constants.SUPPORT_USERNAME));
        to.add(UtilityMethods.getConfString(Constants.INVOICE_NOTIFICATION_ID1));
        tags.add(SupportConstants.INVOICE);
        notification.sendEmail(to, "Invoice Generation Failed", failedResaurantInfo.toString(), tags);
    }

    private Promise<List<Restaurant>> getAllActiveRestaurants(String token) {

        return WS.url(UtilityMethods.getConfString(SupportConstants.CLICKTABLE_URL) + SupportConstants.RESTAURANT_URI)
                .setHeader(SupportConstants.ACCESS_TOKEN, token)
                .setQueryParameter(SupportConstants.STATUS, SupportConstants.ACTIVE)
                .setQueryParameter(SupportConstants.ORDERBY, SupportConstants.GUID).get().map(response -> {
                    JsonNode responsJson = response.asJson();
                    Iterator<JsonNode> restaurantJson = responsJson.get(SupportConstants.LIST).elements();
                    List<Restaurant> restaurantList = new ArrayList<Restaurant>();
                    while (restaurantJson.hasNext()) {
                        restaurantList.add(Json.fromJson(restaurantJson.next(), Restaurant.class));
                    }
                    return restaurantList;
                });

    }

    private String generateInvoiceId(Integer bookNumber) {
        StringBuffer str = new StringBuffer();
        str.append("91");
        str.append(getformattedMonthAndfiscalYear());
        str.append(String.format("%06d", bookNumber));
        return str.toString();
    }

    private String getformattedMonthAndfiscalYear() {
        StringBuffer str = new StringBuffer();

        DateFormat monthFormat = new SimpleDateFormat("MM");
        DateFormat yearFormat = new SimpleDateFormat("yy");

        Integer month = DateTime.now().minusMonths(1).getMonthOfYear();

        if (month > 3) {
            str.append(yearFormat.format(DateTime.now().toDate()));
            str.append(yearFormat.format(DateTime.now().plusYears(1).toDate()));
        } else if (month < 4) {
            str.append(yearFormat.format(DateTime.now().minusYears(1).getYear()));
            str.append(yearFormat.format(DateTime.now().getYear()));

        }

        str.append(monthFormat.format(DateTime.now().minusMonths(1).toDate()));

        return str.toString();
    }

    private void sendMissingPlanAndTaxNotificationToAdmin(List<String> restInfoStr) {

        ArrayList<String> to = new ArrayList<String>();
        ArrayList<String> tags = new ArrayList<String>();

        to.add(UtilityMethods.getConfString(Constants.SUPPORT_USERNAME));
        to.add(UtilityMethods.getConfString(Constants.INVOICE_NOTIFICATION_ID1));
        tags.add(SupportConstants.PLAN_TAX);

        notification.sendEmail(to, "Exception in Invoice Generating - Tax or Plan Missing ", restInfoStr.toString(), tags);
    }

    public Invoice findInvoice(Map<String, Object> params) {
        List<ValidationError> errorList = new ArrayList<ValidationError>();

        List<Invoice> invoices = invoiceDao.findByFields(Invoice.class, params);
        if (invoices.size() == 0) {
            log.warn("Exception in Invoice findInvoice method " + ErrorCodes.INVOICE_DOES_NOT_EXISTS);
            errorList = CustomValidations.populateErrorList(errorList, SupportConstants.INVOICE,
                    UtilityMethods.getErrorMsg(ErrorCodes.INVOICE_DOES_NOT_EXISTS), ErrorCodes.INVOICE_DOES_NOT_EXISTS);
            throw new ServiceValidationException("Exception in Invoice findInvoice method", errorList);
        }
        return invoices.get(0);
    }
}
