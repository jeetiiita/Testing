package com.clicktable.support.dao.intf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clicktable.dao.intf.GenericDao;
import com.clicktable.support.model.Invoice;

/**
 */
@Service
public interface InvoiceDao extends GenericDao<Invoice> {

    Integer maxBookNumber(Map<String, Object> params);


}

