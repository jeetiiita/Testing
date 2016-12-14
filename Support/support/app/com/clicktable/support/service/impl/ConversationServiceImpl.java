package com.clicktable.support.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.DelayQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clicktable.model.Restaurant;
import com.clicktable.response.BaseResponse;
import com.clicktable.response.ErrorResponse;
import com.clicktable.response.GetResponse;
import com.clicktable.response.PostResponse;
import com.clicktable.response.SMSResponse;
import com.clicktable.response.UpdateResponse;
import com.clicktable.support.dao.intf.ConversationDao;
import com.clicktable.support.model.Delivery;
import com.clicktable.support.model.GuestConversation;
import com.clicktable.support.model.PromotionSummary;
import com.clicktable.support.service.intf.ConversationService;
import com.clicktable.support.service.intf.NotificationService;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.SupportResponseCodes;
import com.clicktable.support.validate.ConversationValidator;
import com.clicktable.support.validate.DeliveryValidator;
import com.clicktable.support.validate.PromotionSummaryValidator;
import com.clicktable.util.Constants;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.ValidationError;

import play.Logger;

@Component
public class ConversationServiceImpl implements ConversationService {

	private static final Logger.ALogger log = Logger.of(ConversationServiceImpl.class);
	@Autowired
	PromotionSummaryValidator promotionSummaryValidator;
	@Autowired
	private ConversationDao conversationDao;
	@Autowired
	private ConversationValidator conversationValidator;
	@Autowired
	private DeliveryValidator deliveryValidator;
	@Autowired
	private NotificationService notificationService;

	DelayQueue<Delivery> queue = new DelayQueue<>();

	@Override
	public BaseResponse insertToConversation(GuestConversation conversation) {

		BaseResponse response = null;
		GuestConversation newConversation;

		String mobileNum = conversation.getGuestMobileNum();

		List<ValidationError> listOfError = conversationValidator.validateConversationOnAdd(conversation);

		if (listOfError.isEmpty()) {
			newConversation = conversationDao.create(conversation);
			Map<String, GuestConversation> conversationMap = new HashMap<String, GuestConversation>();
			conversationMap.put(mobileNum, newConversation);

			// Asynchronus call to send SMS
			notificationService.sendSMS(conversationMap, false);
			response = new PostResponse<GuestConversation>(SupportResponseCodes.GUEST_CONVERSATION_ADDED_SUCCESFULLY,
					newConversation.getId().toString());
		} else {
			response = new ErrorResponse(SupportResponseCodes.GUEST_CONVERSATION_ADDITION_FAILURE, listOfError);
		}
		return response;
	}

	@Override
	public BaseResponse getGuestConversation(Map<String, Object> params) {
		BaseResponse response;
		Map<String, Object> validParams = conversationValidator.validateFinderParams(params, GuestConversation.class);
		List<GuestConversation> guestConversation = conversationDao.findByFields(GuestConversation.class, validParams);
		response = new GetResponse<>(SupportResponseCodes.GUEST_CONVERSATION_RECORD_FETCH_SUCCESFULLY,
				guestConversation);
		return response;
	}

	@Override
	public BaseResponse insertToMultipleConversation(GuestConversation conversation, Map<String, String> infoMap) {

		BaseResponse response = null;
		String guestGuid;
		GuestConversation newConversation;

		List<Long> guidList = new ArrayList<Long>();
		List<ValidationError> listOfError = new ArrayList<ValidationError>();

		Map<String, GuestConversation> conversationMap = new HashMap<String, GuestConversation>();
		for (Entry<String, String> guestInfo : infoMap.entrySet()) {

			guestGuid = guestInfo.getValue();

			conversation.setGuestMobileNum(guestInfo.getKey()); // key is mobile
			// no.
			conversation.setGuestGuid(guestGuid); // value is guid

			listOfError.addAll(conversationValidator.validateConversationOnAdd(conversation));

			if (listOfError.isEmpty()) {
				newConversation = conversationDao.create(new GuestConversation(conversation));
				conversationMap.put(newConversation.getGuestMobileNum(), newConversation);
				guidList.add(newConversation.getId());
			}
		}

		// Send Async SMS
		notificationService.sendSMS(conversationMap, true);

		if (listOfError.isEmpty()) {
			response = new PostResponse<GuestConversation>(SupportResponseCodes.GUEST_CONVERSATION_ADDED_SUCCESFULLY,
					guidList.toArray());
		} else {
			response = new ErrorResponse(SupportResponseCodes.GUEST_CONVERSATION_ADDITION_FAILURE, listOfError);
		}
		return response;
	}

	@Override
	public BaseResponse updateGuestConversation(GuestConversation conversation) {

		BaseResponse response = null;
		List<ValidationError> listOfError = conversationValidator.validateConversationOnUpdate(conversation);

		if (listOfError.isEmpty()) {
			GuestConversation newConversation = conversationDao.update(conversation);
			response = new UpdateResponse<GuestConversation>(
					SupportResponseCodes.GUEST_CONVERSATION_UPDATED_SUCCESFULLY, newConversation.getId());
		} else {
			response = new ErrorResponse(SupportResponseCodes.GUEST_CONVERSATION_UPDATION_FAILURE, listOfError);
		}
		return response;
	}

	//batch Saving of the 
	@Override
	public void updateSmsStatus(List<Delivery> deliveryList) {
		for (Delivery delivery : deliveryList) {
			updateSmsStatus(delivery);
		}
	}

	// delivery callback function for gupshup.

	private void updateSmsStatus(Delivery delivery) {

		List<ValidationError> listOfError = deliveryValidator.validateDeliveryOnUpdate(delivery);

		if (listOfError.isEmpty()) {
			String smsID = delivery.getExternalId();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Constants.SMS_ID, smsID);

			List<GuestConversation> conversationList = conversationDao.findByFields(GuestConversation.class, params);

			if (!conversationList.isEmpty()) {
				GuestConversation conversation = conversationList.get(0);
				conversation.setSmsId(delivery.getExternalId());
				conversation.setSmsStatusCause(delivery.getCause());
				conversation.setSmsStatus(delivery.getStatus());
				conversationDao.update(conversation);
				log.debug("GuestConversastion Updated Successfully! guid: {}", conversation.getId());
			} else {
				log.error("Converation delivery Failed as SMS_ID " + smsID + "does not exist\n");
			}
		} else {
			log.warn("Errorlist for the GuestConversation Failure: " + listOfError.toString());
		}

	}

	@Override
	public DelayQueue<Delivery> addDeliveryToQueue(Delivery delivery) {

		int delayTime = Integer.parseInt(UtilityMethods.getConfString(SupportConstants.DELAY_QUEUE_DELAY_TIME));
		delivery.setDelayTime(delayTime);
		queue.put(delivery);
		return queue;
	}

	@Override
	public DelayQueue<Delivery> getQueue() {
		return queue;
	}

	@Override
	public void updateConversationWithSMSResponse(GuestConversation conversation, SMSResponse smsResponse) {
		conversation.setSmsId(smsResponse.getId());

		if (smsResponse.getStatus().equals("success"))
			conversation.setSmsStatus("MSG_SENT");
		else
			conversation.setSmsStatus(smsResponse.getStatus());

		conversation.setSmsStatusCause(smsResponse.getDetails());
		conversationDao.update(conversation);

	}

	@Override
	public List<PromotionSummary> getEventPromotionOfRestaurant(Map<String, Object> params) {

		List<ValidationError> errorList = new ArrayList<ValidationError>();
		Map<String, Object> validParams = promotionSummaryValidator.validateParams(params, errorList);
		if (errorList.isEmpty()) {
			List<PromotionSummary> resultList = conversationDao.getEventPromotionOfRestaurant(validParams);
			return resultList;
		} else
			return null;
	}

	@Override
	public Integer getRestaurantPromotionCountForPeriod(Restaurant restaurant, Map<String, Object> params) {
		List count = conversationDao.getEventPromotionConversationCount(restaurant, params);
		int converastionCount = count.get(0) == null ? 0 : Integer.parseInt(count.get(0).toString());
		return converastionCount;
	}

}
