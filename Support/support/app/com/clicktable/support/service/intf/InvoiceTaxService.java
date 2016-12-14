/**
 *
 */
package com.clicktable.support.service.intf;

import org.springframework.stereotype.Service;

import com.clicktable.support.model.InvoiceLineTax;

/**
 * @author j.yadav
 */
@Service
public interface InvoiceTaxService {

    InvoiceLineTax create(InvoiceLineTax invoiceTax);

}
