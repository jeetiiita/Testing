package com.clicktable.support.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.clicktable.support.service.intf.InvoiceLineService;
import com.clicktable.support.util.SupportConstants;

@Service
public class InvoiceLineServiceFactory {

    @Autowired
    @Qualifier("smsInvoiceLineService")
    private InvoiceLineService smsInvoiceLineService;

    @Autowired
    @Qualifier("dummyInvoiceLineService")
    private InvoiceLineService dummyInvoiceLineService;

    //TODO dummy hatao
    public InvoiceLineService getInvoiceLineService(String itemType) {
        switch (itemType) {
            case SupportConstants.SMS:
                return smsInvoiceLineService;
            case SupportConstants.DUMMY:
                return dummyInvoiceLineService;
            default:
                return smsInvoiceLineService;
        }
    }
}
