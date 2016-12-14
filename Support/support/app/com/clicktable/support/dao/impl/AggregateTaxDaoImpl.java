package com.clicktable.support.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clicktable.support.dao.intf.AggregateTaxDao;
import com.clicktable.support.dao.intf.ItemDao;
import com.clicktable.support.model.AggregateTax;
import com.clicktable.support.model.Item;

/**
 * @author j.yadav
 */
@Component
public class AggregateTaxDaoImpl extends JPADao<AggregateTax> implements AggregateTaxDao {

    @Autowired
    ItemDao itemDao;

    @Override
    public List<Item> getAllItemForAggregateTax(AggregateTax aggregateTax) {

        Map<String, Object> params = new HashMap<String, Object>();
        List<Item> itemList = itemDao.findByFields(Item.class, params);

        List<Item> itemListOfAggregateTax = new ArrayList<Item>();

        for (Item item : itemList) {
            Set<AggregateTax> aggregateTaxes = item.getAggregateTaxes();

            for (AggregateTax aggregate : aggregateTaxes) {
                if (aggregateTax.getId().equals(aggregate.getId())) {
                    itemListOfAggregateTax.add(item);
                }

            }

        }
        return itemListOfAggregateTax;
    }
}
