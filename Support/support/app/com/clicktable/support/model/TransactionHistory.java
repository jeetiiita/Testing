package com.clicktable.support.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
public class TransactionHistory implements Serializable {

	private static final long serialVersionUID = -4052651186803080223L;

	@Id
    @GeneratedValue
    private Long id;
	
	@Required(message=ErrorCodes.TXN_USER_GUID_REQUIRED)
	private String userGuid;
	
	@Required(message=ErrorCodes.TXN_REQST_TYPE_REQUIRED)
	private String requestType;
	
	@Required(message=ErrorCodes.TXN_REQST_URL_REQUIRED)
	private String requestUrl;
	
	@Required(message=ErrorCodes.TXN_STATUS_CODE_REQUIRED)
	private Integer statusCode;

	@Required(message=ErrorCodes.TXN_CREATED_DATE_REQUIRED)
	@JsonFormat(pattern = Constants.TIMESTAMP_FORMAT, timezone = Constants.TIMEZONE)
    private Date createdDate = new Timestamp(new Date().getTime());
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public Date getCreatedDate() {
        return createdDate == null ? null : (Date) createdDate.clone();
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
    }

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

}
