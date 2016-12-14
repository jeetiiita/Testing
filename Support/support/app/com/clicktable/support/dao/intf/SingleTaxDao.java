/**
 *
 */
package com.clicktable.support.dao.intf;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clicktable.dao.intf.GenericDao;
import com.clicktable.support.model.AggregateTax;
import com.clicktable.support.model.SingleTax;

/**
 * @author j.yadav
 */
@Service
public interface SingleTaxDao extends GenericDao<SingleTax> {

    List<Integer> getAggregateTaxForSingleTax(SingleTax singleTax);

}
