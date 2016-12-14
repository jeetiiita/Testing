package com.clicktable.support.dao.impl;

import org.springframework.stereotype.Component;

import com.clicktable.support.dao.intf.InvoiceLineDao;
import com.clicktable.support.model.InvoiceLine;

@Component
public class InvoiceLineDaoImpl extends JPADao<InvoiceLine> implements InvoiceLineDao {


}
