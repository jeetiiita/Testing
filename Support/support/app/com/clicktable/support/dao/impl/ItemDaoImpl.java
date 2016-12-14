package com.clicktable.support.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.clicktable.model.Restaurant;
import com.clicktable.support.dao.intf.ItemDao;
import com.clicktable.support.model.AggregateTax;
import com.clicktable.support.model.Item;
import com.clicktable.support.util.SupportConstants;

@Component
public class ItemDaoImpl extends JPADao<Item> implements ItemDao {

    @Override
    public List<AggregateTax> getAggregateTaxOnItem(Item item, Restaurant restaurant) {
        Date deliveryDate = DateTime.now().withTime(0, 0, 0, 0).minusDays(1).toDate();

        String queryStr = "Select tax from Item as item join item.aggregateTaxes as tax "
                + "WHERE item.id=:id and tax.country=:country "
                + "and tax.status=:status and tax.startDate<=:billDate and tax.state=:state";

        Query query = em.createQuery(queryStr)
                .setParameter("id", item.getId())
                .setParameter("state", restaurant.getPhysicalState())
                .setParameter("country", restaurant.getCountryCode())
                .setParameter("status", SupportConstants.ACTIVE)
                .setParameter("billDate", deliveryDate);

        List<AggregateTax> result = query.getResultList();

        return result;

    }

    @Override
    public List<AggregateTax> getAggregateTaxByCountryForItem(Item item, Restaurant restaurant) {
        Date deliveryDate = DateTime.now().withTime(0, 0, 0, 0).minusDays(1).toDate();

        String queryStr = "Select tax from Item as item join item.aggregateTaxes as tax "
                + "WHERE item.id=:id and tax.country=:country "
                + "and tax.status=:status and tax.startDate<=:billDate and tax.centralTax=:centralTax";

        Query query = em.createQuery(queryStr).setParameter("id", item.getId())
                .setParameter("country", restaurant.getCountryCode())
                .setParameter("status", SupportConstants.ACTIVE)
                .setParameter("billDate", deliveryDate)
                .setParameter("centralTax", true);

        List<AggregateTax> result = query.getResultList();

        return result;

    }

}
