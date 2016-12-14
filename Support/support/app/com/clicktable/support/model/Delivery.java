package com.clicktable.support.model;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import com.clicktable.support.util.ErrorCodes;
import com.clicktable.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author j.yadav
 * @version $Revision: 1.0 $
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Delivery implements Delayed {

	@Required(message = ErrorCodes.DELIVERY_EXTERNAL_ID_REQUIRED)
	@MaxLength(message = ErrorCodes.DELIVERY_EXTERNAL_ID_MAXLENGTH, value = 50)
	private String externalId;

	@JsonFormat(pattern = Constants.TIMESTAMP_FORMAT, timezone = Constants.TIMEZONE)
	private Long deliveredTS;

	@Required(message = ErrorCodes.DELIVERYL_STATUS_REQUIRED)
	@MaxLength(message = ErrorCodes.DELIVERY_STATUS_MAXLENGTH, value = 50)
	private String status;

	@Required(message = ErrorCodes.DELIVERYL_PHONE_REQUIRED)
	@MaxLength(message = ErrorCodes.DELIVERY_PHONE_MAXLENGTH, value = 50)
	private String phoneNo;

	@Required(message = ErrorCodes.DELIVERYL_CAUSE_REQUIRED)
	@MaxLength(message = ErrorCodes.DELIVERY_CAUSE_MAXLENGTH, value = 50)
	private String cause;

	@JsonIgnore
	private int delayTime;

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId.trim();
	}

	public Long getDeliveredTS() {
		return deliveredTS;
	}

	public void setDeliveredTS(Long deliveredTS) {
		this.deliveredTS = deliveredTS;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause.toUpperCase();
	}

	public int getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	@Override
	public String toString() {
		return "Delivery [externalId=" + externalId + ", deliveredTS=" + deliveredTS + ", status=" + status
				+ ", phoneNo=" + phoneNo + ", cause=" + cause + ", delayTime=" + delayTime + "]";
	}

	@Override
	public int compareTo(Delayed delivery) {
		if (this.delayTime < delivery.getDelay(TimeUnit.MILLISECONDS)) {
			return -1;
		} else if (this.delayTime > (delivery.getDelay(TimeUnit.MILLISECONDS))) {
			return 1;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		unit.convert(delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		return 0;
	}

}
