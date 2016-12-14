/**
 *
 */
package com.clicktable.support.service.intf;

import org.springframework.stereotype.Service;

import com.clicktable.model.Restaurant;
import com.clicktable.support.model.InvoiceLine;
import com.clicktable.support.model.PlanItem;

import java.util.Date;

/**
 * @author j.yadav
 */
@Service
public interface InvoiceLineService {

    InvoiceLine create(InvoiceLine invoiceLine);

    InvoiceLine generateInvoiceLine(Restaurant restaurant, PlanItem planItem, Integer lineNumber, String token, Date planStartDate);
}
