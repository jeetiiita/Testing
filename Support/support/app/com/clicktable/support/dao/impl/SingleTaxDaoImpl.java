/**
 *
 */
package com.clicktable.support.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clicktable.support.dao.intf.AggregateTaxDao;
import com.clicktable.support.dao.intf.SingleTaxDao;
import com.clicktable.support.model.AggregateTax;
import com.clicktable.support.model.SingleTax;
import com.clicktable.support.util.SupportConstants;

/**
 * @author j.yadav
 */
@Component
public class SingleTaxDaoImpl extends JPADao<SingleTax> implements SingleTaxDao {

    @Override
    public List<Integer> getAggregateTaxForSingleTax(SingleTax singleTax) {
        String queryString = "select at.id from AggregateTax as at join at.taxLayers as taxLayer where taxLayer.singleTax.id=:id and at.status=:status";

        Query query = em.createQuery(queryString).setParameter(SupportConstants.ID, singleTax.getId())
                .setParameter("status", SupportConstants.ACTIVE);

        List<Integer> result = query.getResultList();

        return result;
    }

}
