package com.clicktable.support.service.intf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clicktable.support.model.SingleTax;

@Service
public interface SingleTaxService {

    SingleTax create(SingleTax singleTax);

    List<SingleTax> getSingleTaxes(Map<String, Object> params);

    boolean disable(Integer id);

}