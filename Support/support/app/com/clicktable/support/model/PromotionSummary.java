package com.clicktable.support.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

import com.clicktable.support.util.ErrorCodes;
import com.clicktable.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Entity
public class PromotionSummary extends BaseEntity {

    private static final long serialVersionUID = -6454535715932883940L;

    @Id
    @GeneratedValue
    private Integer id;

    @JsonFormat(pattern = Constants.DATE_FORMAT, timezone = Constants.TIMEZONE)
    private Date sentDate;

    @Required(message = ErrorCodes.GUESTCONVERSATION_ORIGIN_ID)
    @MaxLength(message = ErrorCodes.GUESTCONVERSATION_ORIGIN_ID_MAXLENGTH, value = 50)
    private String originGuid;

    @Required(message = ErrorCodes.GUESTCONVERSATION_MESSAGE_REQUIRED)
    @MaxLength(message = ErrorCodes.GUESTCONVERSATION_MESSAGE_MAXLENGTH, value = 300)
    private String message;

    private Integer smsCount;

    private Integer chargableSmsCount;

    private Integer successCount;

    private Integer failCount;

    private Integer dndFailCount;

    private Integer pendingCount;

    private String restaurantGuid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSentDate() {
        return sentDate == null ? null : (Date) sentDate.clone();
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate == null ? null : (Date) sentDate.clone();
    }

    public String getOriginGuid() {
        return originGuid;
    }

    public void setOriginGuid(String originGuid) {
        this.originGuid = originGuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(Integer smsCount) {
        this.smsCount = smsCount;
    }

    public Integer getChargableSmsCount() {
        return chargableSmsCount;
    }

    public void setChargableSmsCount(Integer chargableSmsCount) {
        this.chargableSmsCount = chargableSmsCount;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public Integer getDndFailCount() {
        return dndFailCount;
    }

    public void setDndFailCount(Integer dndFailCount) {
        this.dndFailCount = dndFailCount;
    }

    public String getRestaurantGuid() {
        return restaurantGuid;
    }

    public void setRestaurantGuid(String restaurantGuid) {
        this.restaurantGuid = restaurantGuid;
    }

    public Integer getPendingCount() {
        return pendingCount;
    }

    public void setPendingCount(Integer pendingCount) {
        this.pendingCount = pendingCount;
    }

}
