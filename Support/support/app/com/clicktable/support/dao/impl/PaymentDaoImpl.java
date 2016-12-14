package com.clicktable.support.dao.impl;

import org.springframework.stereotype.Service;

import com.clicktable.support.dao.intf.PaymentDao;
import com.clicktable.support.model.Payment;

@Service
public class PaymentDaoImpl extends JPADao<Payment> implements PaymentDao {

}
