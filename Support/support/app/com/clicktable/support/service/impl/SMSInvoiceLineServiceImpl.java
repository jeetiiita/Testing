package com.clicktable.support.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clicktable.support.exception.ServiceValidationException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.clicktable.model.Restaurant;
import com.clicktable.support.dao.intf.ConversationDao;
import com.clicktable.support.dao.intf.PromotionSummaryDao;
import com.clicktable.support.model.PromotionSummary;
import com.clicktable.support.service.intf.ConversationService;
import com.clicktable.support.util.SupportConstants;

@Component("smsInvoiceLineService")
@Qualifier("smsInvoiceLineService")
public class SMSInvoiceLineServiceImpl extends InvoiceLineServiceImpl {

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private PromotionSummaryDao promotionSummaryDao;

    int calculateItemCount(Restaurant restaurant,Date planStartDate) {

        // For previous month only...
        DateTime startDate = DateTime.now().withTime(0, 0, 0, 0).minusMonths(1).dayOfMonth().withMinimumValue();
        DateTime endDate = DateTime.now().withTime(23, 59, 59, 0).minusMonths(1).dayOfMonth().withMaximumValue();

        DateTime tempStartDate = new DateTime(planStartDate);
        if(!(tempStartDate.getMonthOfYear() < startDate.getMonthOfYear())) {
            startDate = tempStartDate.withTime(0, 0, 0, 0);
        }
        else if(tempStartDate.getMonthOfYear() > startDate.getMonthOfYear()) {
            throw new ServiceValidationException("Plan Doesn't exist for this month");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(SupportConstants.STARTDATE, startDate.toDate());
        params.put(SupportConstants.ENDDATE, endDate.toDate());

        Integer count = conversationService.getRestaurantPromotionCountForPeriod(restaurant,params);

        // Save Promotions summary

        params.put(SupportConstants.RESTAURANTGUID, restaurant.getGuid());
        params.put(SupportConstants.STARTDATE, startDate);
        params.put(SupportConstants.ENDDATE, endDate);
        List<PromotionSummary> promotions = conversationService.getEventPromotionOfRestaurant(params);
        if (promotions != null) {
            for (PromotionSummary promotion : promotions) {
                promotionSummaryDao.create(promotion);
            }
        }

        return count;
    }

}
