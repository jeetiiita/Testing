/**
 *
 */
package com.clicktable.support.dao.intf;

import org.springframework.stereotype.Service;

import com.clicktable.dao.intf.GenericDao;
import com.clicktable.support.model.InvoiceLineTax;

/**
 * @author j.yadav
 */
@Service
public interface InvoiceTaxDao extends GenericDao<InvoiceLineTax> {

}
