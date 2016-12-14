/**
 *
 */
package com.clicktable.support.service.intf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clicktable.support.model.AggregateTax;

/**
 * @author j.yadav
 */
@Service
public interface AggregateTaxService {

    AggregateTax create(AggregateTax aggregateTax);

    boolean disable(Integer id);

    List<AggregateTax> getAggregateTax(Map<String, Object> params);
}
