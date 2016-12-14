package com.clicktable.support.dao.intf;

import org.springframework.stereotype.Service;

import com.clicktable.dao.intf.GenericDao;
import com.clicktable.support.model.Payment;

@Service
public interface PaymentDao extends GenericDao<Payment> {

}