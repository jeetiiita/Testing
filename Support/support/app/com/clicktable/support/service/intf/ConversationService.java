package com.clicktable.support.service.intf;

import java.util.List;
import java.util.Map;
import java.util.concurrent.DelayQueue;

import org.springframework.stereotype.Service;

import com.clicktable.model.Restaurant;
import com.clicktable.response.BaseResponse;
import com.clicktable.response.SMSResponse;
import com.clicktable.support.model.Delivery;
import com.clicktable.support.model.GuestConversation;
import com.clicktable.support.model.PromotionSummary;

@Service
public interface ConversationService {
    public BaseResponse insertToConversation(GuestConversation conversation);

    public BaseResponse insertToMultipleConversation(GuestConversation conversation, Map<String, String> infoMap);

    public BaseResponse getGuestConversation(Map<String, Object> params);

    public BaseResponse updateGuestConversation(GuestConversation conversation);

    public void updateConversationWithSMSResponse(
            GuestConversation conversationToUpdate, SMSResponse smsResponse);

    /**
     * @param params
     * @return
     */
    List<PromotionSummary> getEventPromotionOfRestaurant(Map<String, Object> params);

    /**
     * @param restaurant
     * @return
     */
    Integer getRestaurantPromotionCountForPeriod(Restaurant restaurant,Map<String,Object> params);

	/**
	 * @param delivery
	 * @return
	 */
	DelayQueue<Delivery> addDeliveryToQueue(Delivery delivery);

	/**
	 * @return
	 */
	DelayQueue<Delivery> getQueue();

	/**
	 * @param deliveryList
	 */
	void updateSmsStatus(List<Delivery> deliveryList);

}
