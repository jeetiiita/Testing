package com.clicktable.support.validate;

import java.util.List;

import com.clicktable.support.model.GuestConversation;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.util.Constants;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.CustomValidations;
import com.clicktable.validate.EntityValidator;
import com.clicktable.validate.ValidationError;

import play.Logger;

@org.springframework.stereotype.Service
public class ConversationValidator extends EntityValidator<GuestConversation> {

    public List<ValidationError> validateConversationOnAdd(GuestConversation conversation) {
        List<ValidationError> errorList = validateOnAdd(conversation);
        if ((!UtilityMethods.getEnumValues(Constants.GUEST_CONVERSATION_LABEL, Constants.ORIGIN).contains(conversation.getOrigin())) && (conversation.getOrigin() != null)) {
            errorList = CustomValidations.populateErrorList(errorList, Constants.ORIGIN, UtilityMethods.getErrorMsg(ErrorCodes.INVALID_ORIGIN), ErrorCodes.INVALID_ORIGIN);
        }
        if ((!UtilityMethods.getEnumValues(Constants.GUEST_CONVERSATION_LABEL, Constants.SENT_BY).contains(conversation.getSentBy())) && (conversation.getSentBy() != null)) {
            errorList = CustomValidations.populateErrorList(errorList, Constants.SENT_BY, UtilityMethods.getErrorMsg(ErrorCodes.INVALID_SENT_BY), ErrorCodes.INVALID_SENT_BY);
        }
        if (conversation.getGuestMobileNum() != null && !UtilityMethods.isValidNumericNumber(conversation.getGuestMobileNum())) {
            Logger.debug("validating mobile no ");
            errorList = CustomValidations.populateErrorList(errorList, Constants.MOBILE, UtilityMethods.getErrorMsg(ErrorCodes.INVALID_MOBILE_NO), ErrorCodes.INVALID_MOBILE_NO);
            Logger.debug("error list is " + errorList);
        }
        return errorList;
    }

    public List<ValidationError> validateConversationOnUpdate(GuestConversation conversation) {
        List<ValidationError> errorList = validateOnAdd(conversation);
        return errorList;
    }

}
