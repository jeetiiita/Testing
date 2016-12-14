package com.clicktable.support.model;

import com.clicktable.support.util.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Entity
public class GuestConversation extends BaseEntity {

    private static final int DEFAULT_SMS_LENGTH = 160;
    private static final long serialVersionUID = -2095970803742734670L;

    @Id
    @GeneratedValue
    private Long id;
    /**
     * Field restaurantGuid.
     */
    @Required(message = ErrorCodes.GUESTCONVERSATION_REST_GUID)
    @MaxLength(message = ErrorCodes.GUESTCONVERSATION_RESTAURANT_GUID_MAXLENGTH, value = 50)
    private String restaurantGuid;

    /**
     * Field message.
     */
    @Required(message = ErrorCodes.GUESTCONVERSATION_MESSAGE_REQUIRED)
    @MaxLength(message = ErrorCodes.GUESTCONVERSATION_MESSAGE_MAXLENGTH, value = 300)
    private String message;

    /**
     * Field guestGuid.
     */
    @Required(message = ErrorCodes.GUESTCONVERSATION_GUEST_GUID)
    @MaxLength(message = ErrorCodes.GUESTCONVERSATION_GUEST_GUID_MAXLENGTH, value = 50)
    private String guestGuid;

    /**
     * Field guestMobileNum.
     */
    @Required(message = ErrorCodes.GUESTCONVERSATION_GUEST_MOBILE_NUM)
    @MaxLength(message = ErrorCodes.GUESTCONVERSATION_GUEST_MOBILE_MAX_LENGTH, value = 10)
    @MinLength(message = ErrorCodes.GUESTCONVERSATION_GUEST_MOBILE_MIN_LENGTH, value = 10)
    private String guestMobileNum;

    /**
     * Field sentBy.
     */
    @Required(message = ErrorCodes.GUESTCONVERSATION_SENTBY)
    @MaxLength(message = ErrorCodes.GUESTCONVERSATION_SENT_BY_MAXLENGTH, value = 50)
    private String sentBy;

    /**
     * Field origin.
     */
    @Required(message = ErrorCodes.GUESTCONVERSATION_ORIGIN)
    private String origin;

    /**
     * Field originGuid.
     */
    @MaxLength(message = ErrorCodes.GUESTCONVERSATION_ORIGIN_ID_MAXLENGTH, value = 50)
    private String originGuid;

    /**
     * Field smsId.
     */
    @MaxLength(message = ErrorCodes.GUESTCONVERSATION_SMS_ID_MAXLENGTH, value = 255)
    private String smsId;

    /**
     * Field smsStatus.
     */
    @MaxLength(message = ErrorCodes.GUESTCONVERSATION_SMS_STATUS_MAXLENGTH, value = 50)
    private String smsStatus;

    /**
     * Field smsCount.
     */
    @Required(message = ErrorCodes.GUESTCONVERSATION_SMS_STATUS)
    private int smsCount;

    /**
     * Field smsStatusCause.
     */
    @MaxLength(message = ErrorCodes.GUESTCONVERSATION_SMS_CAUSE_MAXLENGTH, value = 255)
    private String smsStatusCause;

    public GuestConversation() {
        super();
    }

    public GuestConversation(GuestConversation conversation) {
        this.setCreatedBy(conversation.getCreatedBy());
        this.setCreatedDate(conversation.getCreatedDate());
        this.setGuestGuid(conversation.getGuestGuid());
        this.setGuestMobileNum(conversation.getGuestMobileNum());
        this.setMessage(conversation.getMessage());
        this.setOrigin(conversation.getOrigin());
        this.setOriginGuid(conversation.getOriginGuid());
        this.setRestaurantGuid(conversation.getRestaurantGuid());
        this.setSentBy(conversation.getSentBy());
        this.setSmsId(conversation.getSmsId());
        this.setSmsStatus(conversation.getSmsStatus());
        this.setSmsStatusCause(conversation.getSmsStatusCause());
        this.setUpdatedBy(conversation.getUpdatedBy());
        this.setUpdatedDate(conversation.getUpdatedDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method getGuestMobileNum.
     *
     * @return String
     */
    public String getGuestMobileNum() {
        return guestMobileNum;
    }

    /**
     * Method setGuestMobileNum.
     *
     * @param guestMobileNum String
     */
    public void setGuestMobileNum(String guestMobileNum) {
        this.guestMobileNum = guestMobileNum;
    }

    /**
     * Method getMessage.
     *
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Method setMessage.
     *
     * @param message String
     */
    public void setMessage(String message) {
        this.message = message;
        smsCount = calculateSmsCount(message);
    }

    /**
     * Method getSentBy.
     *
     * @return String
     */
    public String getSentBy() {
        return sentBy;
    }

    /**
     * Method setSentBy.
     *
     * @param sentBy String
     */
    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    /**
     * Method getOrigin.
     *
     * @return String
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Method setOrigin.
     *
     * @param origin String
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Method getRestaurantGuid.
     *
     * @return String
     */
    public String getRestaurantGuid() {
        return restaurantGuid;
    }

    /**
     * Method setRestaurantGuid.
     *
     * @param restaurantGuid String
     */
    public void setRestaurantGuid(String restaurantGuid) {
        this.restaurantGuid = restaurantGuid;
    }

    /**
     * Method getGuestGuid.
     *
     * @return String
     */
    public String getGuestGuid() {
        return guestGuid;
    }

    /**
     * Method setGuestGuid.
     *
     * @param guestGuid String
     */
    public void setGuestGuid(String guestGuid) {
        this.guestGuid = guestGuid;
    }

    /**
     * Method getOriginGuid.
     *
     * @return String
     */
    public String getOriginGuid() {
        return originGuid;
    }

    /**
     * Method setOriginGuid.
     *
     * @param originGuid String
     */
    public void setOriginGuid(String originGuid) {
        this.originGuid = originGuid;
    }

    /**
     * Method getSmsStatus.
     *
     * @return String
     */
    public String getSmsStatus() {
        return smsStatus;
    }

    /**
     * Method setSmsStatus.
     *
     * @param smsStatus String
     */

    public void setSmsStatus(String smsStatus) {
        this.smsStatus = smsStatus;
    }

    /**
     * Method getSmsCount.
     *
     * @return int
     */

    public int getSmsCount() {
        return smsCount;
    }

    /**
     * Method getSmsStatusCause.
     *
     * @return String
     */

    public String getSmsStatusCause() {
        return smsStatusCause;
    }

    /**
     * Method setSmsStatusCause.
     *
     * @param smsStatusCause String
     */
    public void setSmsStatusCause(String smsStatusCause) {
        this.smsStatusCause = smsStatusCause;
    }

    /**
     * Method getSmsId.
     *
     * @return String
     */
    public String getSmsId() {
        return smsId;
    }

    /**
     * Method setSmsId.
     *
     * @param smsId String
     */
    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    private int calculateSmsCount(String message) {
        //Calculate SMS count: Divide by 161 and add 1
        return message != null ? message.length() / (DEFAULT_SMS_LENGTH + 1) + 1 : 0;
    }

    @Override
    public String toString() {
        return "GuestConversation [id=" + id + ", createdDate="
                + createdDate + ", updatedDate=" + updatedDate + ", createdBy="
                + createdBy + ", updatedBy=" + updatedBy + ", restaurantGuid="
                + restaurantGuid + ", message=" + message + ", guestGuid="
                + guestGuid + ", guestMobileNum=" + guestMobileNum
                + ", sentBy=" + sentBy + ", origin=" + origin + ", originGuid="
                + originGuid + ", smsId=" + smsId + ", smsStatus=" + smsStatus
                + ", smsCount=" + smsCount + ", smsStatusCause="
                + smsStatusCause + "]";
    }

}
