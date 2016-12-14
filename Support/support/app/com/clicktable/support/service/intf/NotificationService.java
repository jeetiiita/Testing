package com.clicktable.support.service.intf;


import java.util.ArrayList;
import java.util.Map;

import com.clicktable.support.model.GuestConversation;


@org.springframework.stereotype.Service
public interface NotificationService {
    //void sendSMSProm(List<String> list, EventPromotion promotion,UserInfoModel userInfo);

    void sendSMS(Map<String, GuestConversation> conversationMap, boolean b);

    void sendEmail(ArrayList<String> to, String subject, String text, ArrayList<String> tags);


    //Map<String, SMSResponse> sendSMSProm(List<String> mobileNumList,
    //		String message, boolean b);
}