package com.clicktable.support.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.clicktable.support.dao.intf.InvoiceDao;
import com.clicktable.support.model.Invoice;
import com.clicktable.support.util.SupportConstants;

@Component
public class InvoiceDaoImpl extends JPADao<Invoice> implements InvoiceDao {

    @Override
    public Integer maxBookNumber(Map<String, Object> params) {
        String queryStr = "Select max(invoice.bookNumber) FROM Invoice as invoice WHERE invoice.finMonth=:finMonth AND invoice.finYear=:finYear";

        Query query = em.createQuery(queryStr)
                .setParameter(SupportConstants.FIN_MONTH, params.get(SupportConstants.FIN_MONTH))
                .setParameter(SupportConstants.FIN_YEAR, params.get(SupportConstants.FIN_YEAR));

        List<Integer> maxBookNumber = query.getResultList();
        if (maxBookNumber.get(0) == null)
            return 0;
        else
            return maxBookNumber.get(0);

    }

}
