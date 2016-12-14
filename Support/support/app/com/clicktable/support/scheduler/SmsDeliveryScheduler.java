/**
 * 
 */
package com.clicktable.support.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clicktable.support.model.Delivery;
import com.clicktable.support.service.intf.ConversationService;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.util.UtilityMethods;

import play.Logger;

/**
 * @author j.yadav
 *
 */
@Component
public class SmsDeliveryScheduler implements Runnable {

	 private static final Logger.ALogger log = Logger.of(SmsDeliveryScheduler.class);
	@Autowired
	private ConversationService conversationService;
	
	
	public SmsDeliveryScheduler(ConversationService conversationService) {
		super();
		this.conversationService = conversationService;
	}


	@Override
	public void run() {
		
		int maxDequeSize = Integer.parseInt(UtilityMethods.getConfString(SupportConstants.DELAY_QUEUE_MAX_DEQUE_SIZE));
		DelayQueue<Delivery> delayQueue = conversationService.getQueue();
		List<Delivery> deliveryList= new ArrayList<>();
		delayQueue.drainTo(deliveryList,maxDequeSize);
		log.debug("Queue Size:{}",deliveryList.size());
		conversationService.updateSmsStatus(deliveryList);
	}

}