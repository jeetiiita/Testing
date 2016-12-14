package com.clicktable.support.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.clicktable.model.Restaurant;
import com.clicktable.support.dao.intf.ConversationDao;
import com.clicktable.support.model.GuestConversation;
import com.clicktable.support.model.PromotionSummary;
import com.clicktable.support.util.SupportConstants;

@Component
public class ConversationDaoImpl extends JPADao<GuestConversation> implements
        ConversationDao {

    @Override
    public List<GuestConversation> getConversationAll() {
        List<GuestConversation> getConversationList = findAll(GuestConversation.class);
        return getConversationList;
    }

    @Override
    public void deleteConversation(String guid) {
        delete(guid);
    }

    @Override
    public GuestConversation getConversation(String guid) {
        GuestConversation conversation = find(GuestConversation.class,
                guid);
        return conversation;
    }


    @Override
    public Object getSuccessGuestConversationOfRestaurant(
            String restaurantGuid, Date startDate, Date endDate,
            String originGuid) {
        String queryString = "SELECT SUM(g.smsCount) FROM GuestConversation AS g where g.restaurantGuid=:restaurantGuid "
                + "AND g.updatedDate >= :startDate AND g.updatedDate <:endDate AND smsStatus=:smsStatus AND origin =:origin And originGuid =:originGuid";
        Query query = em.createQuery(queryString)
                .setParameter(SupportConstants.RESTAURANTGUID, restaurantGuid)
                .setParameter(SupportConstants.STARTDATE, startDate)
                .setParameter(SupportConstants.ENDDATE, endDate)
                .setParameter(SupportConstants.SMS_STATUS, SupportConstants.SUCCESS)
                .setParameter(SupportConstants.ORIGIN, SupportConstants.EVENT_PROMOTION)
                .setParameter(SupportConstants.ORIGIN_GUID, originGuid);

        Object resultList = query.getSingleResult();
        return resultList;
    }

    private Object getFailGuestConversationOfRestaurant(String restaurantGuid,
                                                        Date startDate, Date endDate, String originGuid) {
        String queryString = "SELECT SUM(g.smsCount) FROM GuestConversation AS g where g.restaurantGuid=:restaurantGuid AND"
                + " g.updatedDate >= :startDate AND g.updatedDate <:endDate AND smsStatus=:smsStatus AND smsStatusCause NOT IN(:other,:blockedMask,:dndTimeOut,:dndFail) AND origin =:origin And originGuid =:originGuid";
        Query query = em.createQuery(queryString)
                .setParameter(SupportConstants.RESTAURANTGUID, restaurantGuid)
                .setParameter(SupportConstants.STARTDATE, startDate)
                .setParameter(SupportConstants.ENDDATE, endDate)
                .setParameter(SupportConstants.SMS_STATUS, SupportConstants.FAIL)
                .setParameter("other", SupportConstants.OTHER)
                .setParameter("blockedMask", SupportConstants.BLOCKED_MASK)
                .setParameter("dndFail", SupportConstants.DND_FAIL)
                .setParameter("dndTimeOut", SupportConstants.DND_TIMEOUT)
                .setParameter(SupportConstants.ORIGIN, SupportConstants.EVENT_PROMOTION)
                .setParameter(SupportConstants.ORIGIN_GUID, originGuid);
        Object resultList = query.getSingleResult();
        return resultList;
    }


    private Object getDndGuestConversationOfRestaurant(String restaurantGuid,
                                                       Date startDate, Date endDate, String originGuid) {
        String queryString = "SELECT SUM(g.smsCount) FROM GuestConversation AS g where g.restaurantGuid=:restaurantGuid AND"
                + " g.updatedDate >= :startDate AND g.updatedDate <:endDate AND smsStatus=:smsStatus AND smsStatusCause=:smsStatusCause "
                + "OR smsStatusCause=:dndTimeOut AND origin =:origin And originGuid =:originGuid";

        Query query = em.createQuery(queryString)
                .setParameter(SupportConstants.RESTAURANTGUID, restaurantGuid)
                .setParameter(SupportConstants.STARTDATE, startDate)
                .setParameter(SupportConstants.ENDDATE, endDate)
                .setParameter(SupportConstants.SMS_STATUS, SupportConstants.FAIL)
                .setParameter(SupportConstants.SMS_STATUS_CAUSE, SupportConstants.DND_FAIL)
                .setParameter("dndTimeOut", SupportConstants.DND_TIMEOUT)
                .setParameter(SupportConstants.ORIGIN, SupportConstants.EVENT_PROMOTION)
                .setParameter(SupportConstants.ORIGIN_GUID, originGuid);

        Object resultList = query.getSingleResult();
        return resultList;
    }

    private Object getPendingGuestConversationOfRestaurant(
            String restaurantGuid, Date startDate, Date endDate,
            String originGuid) {
        String queryString = "SELECT SUM(g.smsCount) FROM GuestConversation AS g where g.restaurantGuid=:restaurantGuid AND"
                + " g.updatedDate >= :startDate AND g.updatedDate <:endDate AND smsStatus=:smsStatus AND origin =:origin And originGuid =:originGuid";
        Query query = em.createQuery(queryString)
                .setParameter(SupportConstants.RESTAURANTGUID, restaurantGuid)
                .setParameter(SupportConstants.STARTDATE, startDate)
                .setParameter(SupportConstants.ENDDATE, endDate)
                .setParameter(SupportConstants.SMS_STATUS, SupportConstants.MSG_SENT)
                .setParameter(SupportConstants.ORIGIN, SupportConstants.EVENT_PROMOTION)
                .setParameter(SupportConstants.ORIGIN_GUID, originGuid);

        Object resultList = query.getSingleResult();
        return resultList;
    }

    @Override
    public List<PromotionSummary> getEventPromotionOfRestaurant(
            Map<String, Object> params) {

        List<PromotionSummary> promotionSummaryList = new ArrayList<PromotionSummary>();

        String queryString = "SELECT distinct g.originGuid,g.smsCount, g.message, g.updatedDate FROM"
                + " GuestConversation AS g where g.restaurantGuid=:restaurantGuid AND"
                + " g.updatedDate >= :startDate AND g.updatedDate <:endDate AND origin =:origin ";

        String restaurantGuid = params.get(SupportConstants.RESTAURANTGUID).toString();

        Date startDate = ((DateTime) params.get(SupportConstants.STARTDATE)).toDate();
        Date endDate = ((DateTime) params.get(SupportConstants.ENDDATE)).toDate();


        Query query = em.createQuery(queryString)
                .setParameter(SupportConstants.RESTAURANTGUID, restaurantGuid)
                .setParameter(SupportConstants.STARTDATE, startDate)
                .setParameter(SupportConstants.ENDDATE, endDate)
                .setParameter(SupportConstants.ORIGIN, SupportConstants.EVENT_PROMOTION);

        List<Object[]> resultList = query.getResultList();

        Long successMessage;
        Long failMessage;
        Long dndMessage;
        Long pendingMessage;
        String originGuid;
        Long chargableSmsCount;

        for (Object[] result : resultList) {

            originGuid = (String) result[0];

            successMessage = (Long) getSuccessGuestConversationOfRestaurant(
                    restaurantGuid, startDate, endDate, originGuid);
            successMessage = successMessage == null ? Long.valueOf(0)
                    : successMessage;

            failMessage = (Long) getFailGuestConversationOfRestaurant(
                    restaurantGuid, startDate, endDate, originGuid);

            failMessage = failMessage == null ? new Long(0) : failMessage;

            dndMessage = (Long) getDndGuestConversationOfRestaurant(
                    restaurantGuid, startDate, endDate, originGuid);

            dndMessage = dndMessage == null ? new Long(0) : dndMessage;

            pendingMessage = (Long) getPendingGuestConversationOfRestaurant(
                    restaurantGuid, startDate, endDate, originGuid);

            pendingMessage = pendingMessage == null ? new Long(0)
                    : pendingMessage;

            //Long totalSmsCount = (successMessage + failMessage + pendingMessage + dndMessage);
            chargableSmsCount = successMessage + failMessage;

            PromotionSummary promotionSummary = new PromotionSummary();
            promotionSummary.setDndFailCount(dndMessage.intValue());
            promotionSummary.setChargableSmsCount(chargableSmsCount.intValue());
            promotionSummary.setFailCount(failMessage.intValue());
            promotionSummary.setMessage((String) result[2]);
            promotionSummary.setOriginGuid(originGuid);
            promotionSummary.setSentDate((Date) result[3]);
            promotionSummary.setSmsCount((int) result[1]);
            promotionSummary.setSuccessCount(successMessage.intValue());
            promotionSummary.setRestaurantGuid(restaurantGuid);
            promotionSummary.setPendingCount(pendingMessage.intValue());

            promotionSummaryList.add(promotionSummary);
        }

        return promotionSummaryList;

    }

    @Override
    public List getEventPromotionConversationCount(Restaurant restaurant,Map<String,Object> params) {


       /* Date startDate = DateTime.now().withTime(0, 0, 0, 0).minusMonths(1).dayOfMonth().withMinimumValue().toDate();
        Date endDate = DateTime.now().withTime(23, 59, 59, 0).minusMonths(1).dayOfMonth().withMaximumValue().toDate();
*/
        Date startDate = (Date)params.get("startDate");
        Date endDate = (Date)params.get("endDate");

        String queryStr = "SELECT SUM(c.smsCount) FROM GuestConversation as c WHERE c.restaurantGuid=:restaurantGuid AND c.updatedDate >=:startDate "
                + "AND c.updatedDate <:endDate AND c.origin =:eventPromotion AND c.smsStatusCause IN "
                + "(:success,:fail,:absentSubscriber,:unknownSubscriber,:systemFailure,:callBarred,:serviceDown,:other)";

        Query query = em
                .createQuery(queryStr)
                .setParameter(SupportConstants.STARTDATE, startDate)
                .setParameter(SupportConstants.ENDDATE, endDate)
                .setParameter("eventPromotion", SupportConstants.EVENT_PROMOTION)
                .setParameter("success", SupportConstants.SUCCESS)
                .setParameter("fail", SupportConstants.FAIL)
                .setParameter("absentSubscriber", SupportConstants.ABSENT_SUBSCRIBER)
                .setParameter("unknownSubscriber", SupportConstants.UNKNOWN_SUBSCRIBER)
                .setParameter("systemFailure", SupportConstants.SYSTEM_FAILURE)
                .setParameter("callBarred", SupportConstants.CALL_BARRED)
                .setParameter("serviceDown", SupportConstants.SERVICE_DOWN)
                .setParameter("other", SupportConstants.OTHER)
                .setParameter(SupportConstants.RESTAURANT_GUID, restaurant.getGuid());


        List count = query.getResultList();

        return count;
    }
}
