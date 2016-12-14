package com.clicktable.support.service.intf;

import org.springframework.stereotype.Service;

import com.clicktable.support.model.Invoice;
import com.clicktable.support.model.Payment;

@Service
public interface InvoicePdfService {

    public void generateInvoicePdf(Invoice invoice);

}
