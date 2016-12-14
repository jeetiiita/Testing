package com.clicktable.support.dao.intf;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clicktable.dao.intf.GenericDao;
import com.clicktable.model.Restaurant;
import com.clicktable.support.model.AggregateTax;
import com.clicktable.support.model.Item;

@Service
public interface ItemDao extends GenericDao<Item> {

    List<AggregateTax> getAggregateTaxOnItem(Item item, Restaurant restaurant);

    List<AggregateTax> getAggregateTaxByCountryForItem(Item item, Restaurant restaurant);

}
