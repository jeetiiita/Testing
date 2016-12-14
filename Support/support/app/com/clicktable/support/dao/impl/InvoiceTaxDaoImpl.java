/**
 *
 */
package com.clicktable.support.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import play.Logger;

import com.clicktable.support.dao.intf.InvoiceTaxDao;
import com.clicktable.support.model.Invoice;
import com.clicktable.support.model.InvoiceLineTax;
import com.clicktable.support.util.SupportConstants;

/**
 * @author j.yadav
 */
@Component
public class InvoiceTaxDaoImpl extends JPADao<InvoiceLineTax> implements InvoiceTaxDao {

    private static final Logger.ALogger log = Logger.of(InvoiceTaxDaoImpl.class);

    @Override
    protected TypedQuery<InvoiceLineTax> createQuery(Class type, Map<String, Object> params) {
        log.debug("in create query");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<InvoiceLineTax> cq = cb.createQuery(type);

        Root<InvoiceLineTax> root = cq.from(InvoiceLineTax.class);
        Join<InvoiceLineTax, Invoice> rt = root.join("invoice");
        List<Predicate> predicates = new ArrayList<Predicate>();
        setPredicates(predicates, cb, root, params);
        predicates.add(cb.equal(rt.<String>get(SupportConstants.GUID), params.get(SupportConstants.INVOICEGUID)));
        cq.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<InvoiceLineTax> pquery = em.createQuery(cq);

        int pageSize = getPageSize(params);
        pquery.setFirstResult(getIndex(params, pageSize));
        pquery.setMaxResults(pageSize);

        return pquery;
    }

}
