package com.clicktable.support.service.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import play.Logger;
import play.libs.Akka;
import play.libs.Time.CronExpression;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import com.clicktable.support.service.intf.ConversationService;
import com.clicktable.support.scheduler.InvoiceGenerationScheduler;
import com.clicktable.support.scheduler.SmsDeliveryScheduler;
import com.clicktable.support.service.intf.InvoicePdfService;
import com.clicktable.support.service.intf.InvoiceService;
import com.clicktable.support.service.intf.ThreadSchedulerService;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.util.Constants;
import com.clicktable.util.UtilityMethods;

/**
 * @author s.gupta
 */
@Service
public class ThreadSchedulerServiceImpl implements ThreadSchedulerService {

    private static final Logger.ALogger log = Logger.of(ThreadSchedulerServiceImpl.class);
    @Autowired
    private InvoicePdfService invoicePdfService;
    
    @Autowired
    private InvoiceService invoiceService;
    
    @Autowired
    private ConversationService conversationService;

    @Override
    public void startThreads() {
        startThreadEarlyMorning(new InvoiceGenerationScheduler(invoiceService, invoicePdfService));
        processDelayQueueOfSmsDelivery();
      }
    
    private void startThread(Runnable thread) {
        boolean enable = Boolean.parseBoolean(UtilityMethods.getProperty(Constants.SESSION_CONFIG, thread.getClass().getSimpleName(), Constants.ENABLE));
        if (enable) {
            int start = Integer.parseInt(UtilityMethods.getProperty(Constants.SESSION_CONFIG, thread.getClass().getSimpleName(), Constants.START).trim());
            int duration = Integer.parseInt(UtilityMethods.getProperty(Constants.SESSION_CONFIG, thread.getClass().getSimpleName(), Constants.DURATION).trim());
            Logger.info("starting scheduler: " + thread.getClass().getSimpleName());
            log.debug("starting scheduler: " + thread.getClass().getSimpleName());
            log.info("starting scheduler: " + thread.getClass().getSimpleName());
            Akka.system().scheduler().schedule(Duration.create(start, TimeUnit.SECONDS), Duration.create(duration, TimeUnit.SECONDS), thread, Akka.system().dispatcher());
        }

    }

    private void startThreadEarlyMorning(Runnable thread) {

        try {
            boolean enable = Boolean.parseBoolean(UtilityMethods.getProperty(Constants.SESSION_CONFIG, thread.getClass().getSimpleName(), Constants.ENABLE));
            if (enable) {
                log.info(" startThreadEarlyMorning SCHEDULAR STARTS  =========== " + new Date(Calendar.getInstance().getTimeInMillis()));
                CronExpression cronExpression = new CronExpression(UtilityMethods.getProperty(Constants.SESSION_CONFIG, thread.getClass().getSimpleName(), Constants.CRON_EXPRESSION_TO_LAUNCH_THREAD)
                        .trim());

                Date nextValidTimeAfter = cronExpression.getNextValidTimeAfter(new Date());
                log.info(" NEXT startThreadEarlyMorning SCHEDULAR STARTS ============" + nextValidTimeAfter);
                log.debug("NEXT startThreadEarlyMorning SCHEDULAR STARTS ============" + nextValidTimeAfter);

                FiniteDuration finiteDuration = Duration.create(nextValidTimeAfter.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);

                int restart = Integer.parseInt(UtilityMethods.getProperty(Constants.SESSION_CONFIG, thread.getClass().getSimpleName(), Constants.RESTART).trim());
                Akka.system().scheduler().schedule(finiteDuration, Duration.create(restart, TimeUnit.SECONDS), thread, Akka.system().dispatcher());
            }


        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private void processDelayQueueOfSmsDelivery()
    {
    	int dequeTime = Integer.parseInt(UtilityMethods.getConfString(SupportConstants.DELAY_QUEUE_DEQUE_TIME));
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new SmsDeliveryScheduler(conversationService), 0, dequeTime, TimeUnit.SECONDS);
    }
}
