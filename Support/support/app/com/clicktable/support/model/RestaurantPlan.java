package com.clicktable.support.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class RestaurantPlan extends BaseEntity {

    private static final long serialVersionUID = -3540679197489897589L;

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @Required(message = ErrorCodes.PLAN_REQUIRED)
    @JoinColumn(name = "planId")
    private Plan plan;

    @Required(message = ErrorCodes.REST_GUID_REQUIRED)
    private String restaurantGuid;

    @Required(message = ErrorCodes.REST_PLAN_START_DATE_REQUIRED)
    @JsonFormat(pattern = Constants.DATE_FORMAT, timezone = Constants.TIMEZONE)
    private Date startDate;

    @JsonFormat(pattern = Constants.DATE_FORMAT, timezone = Constants.TIMEZONE)
    private Date endDate;

    @Required(message = ErrorCodes.REST_PLAN_STATUS_REQUIRED)
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public String getRestaurantGuid() {
        return restaurantGuid;
    }

    public void setRestaurantGuid(String restGuid) {
        this.restaurantGuid = restGuid;
    }

    public Date getStartDate() {
        return startDate == null ? null : (Date) startDate.clone();
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate == null ? null : (Date) startDate.clone();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
