package com.clicktable.support.scheduler;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import play.Logger;
import play.libs.F.Promise;
import play.libs.ws.WS;

import com.clicktable.support.service.intf.InvoicePdfService;
import com.clicktable.support.service.intf.InvoiceService;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.util.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class InvoiceGenerationScheduler implements Runnable {

    private static final Logger.ALogger logger = Logger.of(InvoiceGenerationScheduler.class);
    private static Lock lock = new ReentrantLock();
    //@Autowired
    InvoiceService invoiceService;
    //@Autowired
    InvoicePdfService invoicePdfService;


    public InvoiceGenerationScheduler() {
    }

    // Invoice Scheduler
    public InvoiceGenerationScheduler(InvoiceService invoiceService, InvoicePdfService invoicePdfService) {
        this.invoiceService = invoiceService;
        this.invoicePdfService = invoicePdfService;
    }

    @Override
    public void run() {
        runInvoiceGenScheduler();
    }

    public Integer runInvoiceGenScheduler() {

        if (InvoiceGenerationScheduler.lock.tryLock()) {
            try {
                Promise<String> tokenMap = loginWithAdmin();
                tokenMap.onRedeem(token -> {
                    logger.info("Invoice ! Inside Promise Call...");
                    if (token != null) {
                        Logger.debug("call to insertDailyInvoiceTxn");
                        invoiceService.generateAllInvoices(token);
                    }
                });

                tokenMap.onFailure(t -> {
                    throw t;
                });

            } catch (Throwable e) {
                logger.error("Error while generating Invoice", e);
            } finally {
                InvoiceGenerationScheduler.lock.unlock();
            }
        }
        return 0;

    }

    private Promise<String> loginWithAdmin() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jNode = mapper.createObjectNode();

        jNode.put(SupportConstants.USERNAME, UtilityMethods.getConfString(SupportConstants.CLICKTABLE_USER)).put(SupportConstants.PASSWORD,
                UtilityMethods.getConfString(SupportConstants.CLICKTABLE_PASSWORD));

        Promise<String> accResponse = WS
                .url(UtilityMethods.getConfString(SupportConstants.CLICKTABLE_URL) + SupportConstants.LOGIN_URI).post(jNode)
                .map(accessResponse -> {

                    Logger.info("Invoice ! Inside Promise Call...");

                    JsonNode responseJson = accessResponse.asJson();

                    String token = responseJson.get(SupportConstants.TOKEN).asText();

                    return token;
                });
        return accResponse;
    }
}
