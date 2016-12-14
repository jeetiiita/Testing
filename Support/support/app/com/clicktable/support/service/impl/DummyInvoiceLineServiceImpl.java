package com.clicktable.support.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.clicktable.model.Restaurant;

import java.util.Date;

@Component("dummyInvoiceLineService")
@Qualifier("dummyInvoiceLineService")
public class DummyInvoiceLineServiceImpl extends InvoiceLineServiceImpl {

    @Override
    int calculateItemCount(Restaurant restaurant,Date planStartDate) {
        return 10;
    }

}
