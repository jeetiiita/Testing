package com.clicktable.support.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import play.Logger;
import play.libs.F;

import com.clicktable.response.BaseResponse;
import com.clicktable.response.PostResponse;
import com.clicktable.support.scheduler.InvoiceGenerationScheduler;
import com.clicktable.support.service.intf.InvoicePdfService;
import com.clicktable.support.service.intf.InvoiceService;
import com.clicktable.support.service.intf.SchedulerService;
import com.clicktable.util.ResponseCodes;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    //@Autowired
    //InvoiceGenerationScheduler invoiceGenerationScheduler;

    private static final Logger.ALogger log = Logger.of(SchedulerServiceImpl.class);
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    InvoicePdfService invoicePdfService;

    @Override
    public BaseResponse runInvoiceGenrationScheduler(Map<String, Object> params) {
        BaseResponse response = null;
        try {
            F.Promise.promise(() -> new InvoiceGenerationScheduler(invoiceService, invoicePdfService).runInvoiceGenScheduler());
            response = new PostResponse<>(ResponseCodes.SHEDULER_JOB_RUN_SUCCESSFULLY, "");
        } catch (Exception e) {
            log.error("Exception in running job", e);
        }

        return response;
    }

}
