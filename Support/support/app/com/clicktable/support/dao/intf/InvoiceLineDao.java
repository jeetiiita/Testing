package com.clicktable.support.dao.intf;

import org.springframework.stereotype.Service;

import com.clicktable.dao.intf.GenericDao;
import com.clicktable.support.model.InvoiceLine;

@Service
public interface InvoiceLineDao extends GenericDao<InvoiceLine> {

}
