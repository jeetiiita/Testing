package com.clicktable.support.service.intf;

import com.clicktable.model.Restaurant;
import com.clicktable.support.model.Invoice;
import com.clicktable.support.model.Payment;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public interface InvoiceService {

    List<Invoice> getInvoice(Map<String, Object> params);

    Invoice create(Invoice invoice);

    Invoice getInvoiceById(Long id);

    void generateAllInvoices(String token);

    void updateInvoice(Payment payment);

    void generateInvoice(Restaurant restaurant, Integer bookNumber, String token);

    Invoice findInvoice(Map<String, Object> params);
}
