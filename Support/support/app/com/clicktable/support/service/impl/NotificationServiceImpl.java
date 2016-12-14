package com.clicktable.support.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clicktable.response.SMSResponse;
import com.clicktable.support.model.GuestConversation;
import com.clicktable.support.service.intf.ConversationService;
import com.clicktable.support.service.intf.NotificationService;
import com.clicktable.util.Constants;
import com.clicktable.util.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.common.collect.Lists;
import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;

import play.Logger;
import play.libs.Json;
import play.libs.ws.WS;

/**
 * @author s.gupta
 */
@Component
public class NotificationServiceImpl implements NotificationService {

    private static final Logger.ALogger log = Logger.of(NotificationServiceImpl.class);
    MandrillApi mandrillApi = UtilityMethods.getMandrillApi();
    @Autowired
    private ConversationService conversationService;

    @Override
    public void sendSMS(Map<String, GuestConversation> conversationMap,
                        boolean b) {
        String urlString = "http://enterprise.smsgupshup.com/GatewayAPI/rest";

        if (conversationMap.isEmpty())
            return;
        log.info("Sending sms using gupshup");
        GuestConversation conversation = conversationMap.values().stream().findFirst().get();
        List<String> mobileNoList = Lists.newArrayList(conversationMap.keySet());
        List<List<String>> numbersList = Lists.partition(mobileNoList, 100);
        String userId;
        String password;
        if (conversation.getOrigin().equals(Constants.EVENT_PROMOTION_ENUM_VALUE)) {
            userId = UtilityMethods.getConfString(Constants.GUPSHUP_PROMOTION_USER_ID);
            password = UtilityMethods.getConfString(Constants.GUPSHUP_PROMOTION_PASSWORD);
        } else {
            userId = UtilityMethods.getConfString(Constants.GUPSHUP_USER_ID);
            password = UtilityMethods.getConfString(Constants.GUPSHUP_PASSWORD);
        }

        for (List<String> listNumbers : numbersList) {
            //for(String listNumbers:listOfListNumbers)  {
            String numbers = String.join(",91", listNumbers);
            WS.url(urlString)
                    .setQueryParameter("send_to", 91 + numbers)
                    .setQueryParameter("msg", conversation.getMessage())
                    .setQueryParameter("msg_type", "TEXT")
                    .setQueryParameter("format", "JSON")
                    .setQueryParameter("v", "1.1")
                    .setQueryParameter("method", "SendMessage")
                    .setQueryParameter("userid", userId)
                    .setQueryParameter("password", password)
                    .get()
                    .map(response -> {

                        JsonNode jsonNode = response.asJson();
                        log.debug("Response from Gupshup: {}", jsonNode);
                        //	if (jsonNode.get("response").get("status").textValue().equalsIgnoreCase("success")) {
                        if (jsonNode.has("data")) {
                            ArrayNode data = (ArrayNode) jsonNode.get("data").get("response_messages");
                            data.forEach(resp -> {
                                SMSResponse smsResponse = Json.fromJson(resp, SMSResponse.class);
                                GuestConversation conversationToUpdate = conversationMap.get(smsResponse.getPhone().substring(2, smsResponse.getPhone().length()));
                                conversationService.updateConversationWithSMSResponse(conversationToUpdate, smsResponse);
                            });
                        } else {
                            SMSResponse smsResponse = Json.fromJson(jsonNode.get("response"), SMSResponse.class);
                            conversationService.updateConversationWithSMSResponse(conversation, smsResponse);
                        }
                        /*else{
							//TODO handle failure/waiting status from Gupshup
						}*/
                        return jsonNode;
                    });

        }
        log.info("Sending sms completed");
    }

    @Override
    public void sendEmail(ArrayList<String> to, String subject, String text, ArrayList<String> tags) {
        try {
            // create your message
            MandrillMessage message = new MandrillMessage();
            message.setSubject(subject);
            message.setText(text);
            message.setAutoText(true);
            message.setFromEmail(UtilityMethods.getServerConfigString(Constants.SMTP_USER));


            // add recipients
            ArrayList<Recipient> recipients = new ArrayList<Recipient>();
            to.forEach(emailId -> {
                Recipient recipient = new Recipient();
                recipient.setEmail(emailId);
                recipients.add(recipient);
            });
            message.setTo(recipients);

            message.setPreserveRecipients(true);

            if (!tags.isEmpty())
                message.setTags(tags);

            // message.setAttachments(attachments);

            MandrillMessageStatus[] messageStatusReports = mandrillApi.messages().send(message, false);
        } catch (MandrillApiError | IOException e) {


            log.info(".........................error............................");
            // TODO Auto-generated catch block
            play.Logger.error(e.getMessage());
            e.printStackTrace();
        }

    }

}
