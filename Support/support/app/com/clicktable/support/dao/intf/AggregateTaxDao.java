/**
 *
 */
package com.clicktable.support.dao.intf;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clicktable.dao.intf.GenericDao;
import com.clicktable.support.model.AggregateTax;
import com.clicktable.support.model.Item;

/**
 * @author j.yadav
 */
@Service
public interface AggregateTaxDao extends GenericDao<AggregateTax> {

    List<Item> getAllItemForAggregateTax(AggregateTax aggregateTax);

}
