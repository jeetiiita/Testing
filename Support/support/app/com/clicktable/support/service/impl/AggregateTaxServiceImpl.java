package com.clicktable.support.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clicktable.support.dao.intf.AggregateTaxDao;
import com.clicktable.support.exception.ServiceValidationException;
import com.clicktable.support.model.AggregateTax;
import com.clicktable.support.model.Item;
import com.clicktable.support.service.intf.AggregateTaxService;
import com.clicktable.support.service.intf.NotificationService;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.util.Constants;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.CustomValidations;
import com.clicktable.validate.ValidationError;

import play.Logger;

/**
 * @author j.yadav
 */
@Component
public class AggregateTaxServiceImpl implements AggregateTaxService {

    private static final Logger.ALogger log = Logger
            .of(AggregateTaxServiceImpl.class);
    @Autowired
    NotificationService notification;
    @Autowired
    private AggregateTaxDao aggregateTaxDao;

    @Override
    public AggregateTax create(AggregateTax aggregateTax) {
        AggregateTax aggregateTaxEntity = aggregateTaxDao.create(aggregateTax);
        return aggregateTaxEntity;
    }

    @Override
    public List<AggregateTax> getAggregateTax(Map<String, Object> params) {
        List<AggregateTax> aggregateTaxList = aggregateTaxDao.findByFields(AggregateTax.class, params);
        return aggregateTaxList;
    }


    @Override
    public boolean disable(Integer id) {
        List<ValidationError> errorList = new ArrayList<ValidationError>();
        AggregateTax aggregateTax = aggregateTaxDao.find(id);

        if (aggregateTax == null) {
            errorList = CustomValidations.populateErrorList(errorList, SupportConstants.ID, UtilityMethods.getErrorMsg(ErrorCodes.ID_DOES_NOT_EXIST), ErrorCodes.ID_DOES_NOT_EXIST);

            throw new ServiceValidationException("Exception in AggregateTaxService disable Function", errorList);
        }
        if (aggregateTax.getStatus().equals(SupportConstants.INACTIVE_STATUS)) {
            errorList = CustomValidations.populateErrorList(errorList, SupportConstants.STATUS, UtilityMethods.getErrorMsg(ErrorCodes.STATUS_ALREADY_INACTIVE), ErrorCodes.STATUS_ALREADY_INACTIVE);

            throw new ServiceValidationException("Exception in AggregateTaxService disable Function", errorList);
        }

        log.info(aggregateTax.toString());
        List<Item> itemList = aggregateTaxDao.getAllItemForAggregateTax(aggregateTax);

        sendNotificationToAdmin(itemList, aggregateTax);

        aggregateTax.setStatus(SupportConstants.INACTIVE_STATUS);
        aggregateTax.setEndDate(DateTime.now().toDate());
        aggregateTax.setUpdatedDate(DateTime.now().toDate());
        aggregateTaxDao.update(aggregateTax);
        return true;
    }

    private void sendNotificationToAdmin(List<Item> itemList, AggregateTax aggregateTax) {

        StringBuilder message = new StringBuilder();
        message.append("Hi Please Updated Items for AggregateTax { ");
        message.append(System.getProperty("line.separator"));

        message.append(aggregateTax.toString() + " } has been disabled");
        message.append(System.getProperty("line.separator"));

        message.append(System.getProperty("line.separator"));

        for (Item item : itemList) {
            message.append(item.toString());
            message.append(System.getProperty("line.separator"));
        }

        ArrayList<String> to = new ArrayList<String>();
        ArrayList<String> tags = new ArrayList<String>();
        to.add(UtilityMethods.getConfString(Constants.SUPPORT_USERNAME));
        to.add(UtilityMethods.getConfString(Constants.INVOICE_NOTIFICATION_ID1));

        tags.add(SupportConstants.AGGRGEGATE_TAX);

        notification.sendEmail(to, "AggregateTax Disabled", message.toString(), tags);
    }

}

