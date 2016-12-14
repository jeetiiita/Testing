package com.clicktable.support.dao.intf;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clicktable.dao.intf.GenericDao;
import com.clicktable.model.Restaurant;
import com.clicktable.support.model.GuestConversation;
import com.clicktable.support.model.PromotionSummary;

/**
 */
@Service
public interface ConversationDao extends GenericDao<GuestConversation> {

    /**
     * @return
     */
    List<GuestConversation> getConversationAll();

    /**
     * @param guid
     */
    void deleteConversation(String guid);

    /**
     * @param params
     * @return
     */
    List<PromotionSummary> getEventPromotionOfRestaurant(
            Map<String, Object> params);

    /**
     * @param guid
     * @return
     */
    GuestConversation getConversation(String guid);

    Object getSuccessGuestConversationOfRestaurant(String restaurantGuid,
                                                   Date startDate, Date endDate, String originGuid);

    List getEventPromotionConversationCount(Restaurant restaurant,Map<String, Object> params);

}
